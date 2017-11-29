package com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.reward.pojo.RewardModel;

import org.parceler.Parcels;

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
}
