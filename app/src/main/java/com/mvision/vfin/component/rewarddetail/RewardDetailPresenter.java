package com.mvision.vfin.component.rewarddetail;


import android.os.Bundle;

import com.mvision.vfin.api.response.RewardDetailResponseModel;
import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.reward.RewardManage;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailPresenter extends Presenter<RewardDetailContract.View> implements
        RewardDetailContract.Presenter {

    private RewardDetailContract.View view;
    private RewardModel rewardModel;
    private RewardDetailResponseModel rewardDetailResponseModel ;
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        rewardModel = Parcels.unwrap(extras.getParcelable("reward"));
    }

    public RewardDetailPresenter(RewardDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getRewardDetail() {
        RewardDetailManage.getInstance(callBackData).getRewardDetail(rewardModel.id);

    }

    @Override
    public void getRewardDetailBuy() {
        view.showRewardDetailBuy(rewardModel);
    }

    @Override
    public void getCoin() {
        RewardDetailManage.getInstance(callBackData).getCoin();
    }

    @Override
    public void stopRealTime() {
        RewardDetailManage.getInstance(callBackData).stopRealTime();
    }

    @Override
    public void getFullDetail() {
        view.showFullDetail(rewardDetailResponseModel.result.fullDetails);
    }

    @Override
    public void buyProduct() {
        RewardDetailManage.getInstance(callBackData).tradeBuyProduct(rewardModel);
    }

    private RewardDetailManage.CallBackData callBackData = new RewardDetailManage.CallBackData() {
        @Override
        public void getCoin(ModelCoinAndBit modelCoinAndBit) {
            view.setCoin(modelCoinAndBit);
        }

        @Override
        public void trade(TradeBuyResponseModel tradeBuyResponseModel) {
        }

        @Override
        public void rewardDetail(RewardDetailResponseModel rewardDetailResponseModel) {
            RewardDetailPresenter.this.rewardDetailResponseModel = rewardDetailResponseModel;
            view.showRewardDetail(rewardDetailResponseModel);
        }
    };
}
