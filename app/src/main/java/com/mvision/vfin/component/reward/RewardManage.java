package com.mvision.vfin.component.reward;


import com.mvision.vfin.api.request.Market;
import com.mvision.vfin.api.response.ListRedemtionResponseModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class RewardManage {
    private static RewardManage instance = null;
    public static RewardManage getInstance() {
        if (instance == null)
            instance = new RewardManage();
        return instance;
    }

    public void getRewardList(final Query.CallBackData callBackData){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Market.class).getListRedemption().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListRedemtionResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getRewardList(callBackData);
                            }
                        });

                    }

                    @Override
                    public void onNext(ListRedemtionResponseModel s) {
                        callBackData.onSuccess(s.result);

                    }

                });
    }

}
