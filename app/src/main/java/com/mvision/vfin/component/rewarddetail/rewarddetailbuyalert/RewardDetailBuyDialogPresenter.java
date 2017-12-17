package com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert;

import android.os.Bundle;

import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/16/2017 AD.
 */

public class RewardDetailBuyDialogPresenter extends Presenter<RewardDetailBuyDialogContract.View>
        implements  RewardDetailBuyDialogContract.Presenter{

    private RewardDetailBuyDialogContract.View view ;
    private RewardModel rewardModel ;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        rewardModel = Parcels.unwrap(extras.getParcelable("RewardDetail"));
    }

    public RewardDetailBuyDialogPresenter(RewardDetailBuyDialogContract.View view){
        this.view = view ;
    }

    @Override
    public void getRewardDetailBuy() {
        view.showRewardDetailBuy(rewardModel);
    }

    @Override
    public void tradeProduct() {
        RewardBuylManage.getInstance().tradeBuyProduct(rewardModel, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.tradeProductSuccess((TradeBuyResponseModel) t);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
