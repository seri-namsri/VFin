package com.mvision.vfin.component.rewarddetail;


import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

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

    }

    @Override
    public void getRewardDetailBuy() {
        view.showRewardDetailBuy(rewardModel);
    }
}
