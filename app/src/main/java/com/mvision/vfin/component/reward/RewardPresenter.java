package com.mvision.vfin.component.reward;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.reward.pojo.CategoryModel;
import com.mvision.vfin.component.reward.pojo.ModelRewardMerge;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class RewardPresenter extends Presenter<RewardContract.View> implements RewardContract.Presenter{

    private RewardContract.View view ;

    public RewardPresenter(RewardContract.View view){
        this.view = view ;
    }

    @Override
    public void getReward() {
        view.showLoading();

    }
}
