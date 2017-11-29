package com.mvision.vfin.component.rewarddetail;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.reward.pojo.RewardModel;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public interface RewardDetailContract {
    interface View extends BaseView {
        void showRewardDetail(RewardModel rewardModel);
        void showRewardDetailBuy(RewardModel rewardModel);
    }

    interface Presenter {
        void getRewardDetail();
        void getRewardDetailBuy();

    }

}
