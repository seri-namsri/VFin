package com.example.enter_01.vfin.component.reward;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.reward.pojo.ModelRewardMerge;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface RewardContract {

    interface View extends BaseView {

        void setUpViewReward(ArrayList<ModelRewardMerge> modelList);
    }

    interface Presenter {
        void getReward();

    }
}
