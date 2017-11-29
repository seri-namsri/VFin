package com.mvision.vfin.component.reward;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.reward.pojo.ModelRewardMerge;

import java.util.ArrayList;

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
