package com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert;

import com.mvision.vfin.api.modelrequest.TradeBuy;
import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardBuylManage {

    private static RewardBuylManage instance = null;
    public static RewardBuylManage getInstance() {
        if (instance == null)
            instance = new RewardBuylManage();
        return instance;
    }



    public void tradeBuyProduct(final RewardModel rewardModel,final Query.CallBackData callBackData) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Product.class)
                .tradeBuy(new TradeBuy(PreferencesMange.getInstance().getMemberID(),null,
                        rewardModel.id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TradeBuyResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TradeBuyResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }
}
