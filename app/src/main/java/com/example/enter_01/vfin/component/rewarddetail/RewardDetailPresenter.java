package com.example.enter_01.vfin.component.rewarddetail;


import android.os.Bundle;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailPresenter extends Presenter<RewardDetailContract.View> implements
        RewardDetailContract.Presenter {

    private RewardDetailContract.View view;
    private String reward_id;
    private RewardModel rewardModel;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        reward_id = extras.getString("reward_id");
    }

    public RewardDetailPresenter(RewardDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getRewardDetail() {
        RewardDetailManage.getInstance().getRewardDetail(reward_id, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                rewardModel = (RewardModel) t;
                view.showRewardDetail(rewardModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getRewardDetailBuy() {
        view.showRewardDetailBuy(rewardModel);
    }
}
