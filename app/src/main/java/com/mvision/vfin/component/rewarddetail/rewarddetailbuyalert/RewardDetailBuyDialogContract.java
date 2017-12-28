package com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.reward.pojo.RewardModel;

/**
 * Created by enter_01 on 11/16/2017 AD.
 */

public interface RewardDetailBuyDialogContract {

    interface View extends BaseView {
        void showRewardDetailBuy(RewardModel rewardModel);
        void tradeProductSuccess(TradeBuyResponseModel rewardModel);

    }

    interface Presenter {
        void getRewardDetailBuy();
        void tradeProduct();


    }

}
