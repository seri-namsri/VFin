package com.example.enter_01.vfin.component.rewarddetail;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;

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
