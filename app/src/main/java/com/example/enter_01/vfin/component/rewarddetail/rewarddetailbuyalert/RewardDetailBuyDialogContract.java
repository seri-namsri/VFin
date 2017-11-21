package com.example.enter_01.vfin.component.rewarddetail.rewarddetailbuyalert;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;

/**
 * Created by enter_01 on 11/16/2017 AD.
 */

public interface RewardDetailBuyDialogContract {

    interface View extends BaseView {
        void showRewardDetailBuy(RewardModel rewardModel);
    }

    interface Presenter {
        void getRewardDetailBuy();

    }

}
