package com.mvision.vfin.component.rewarddetail;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.RewardDetailResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.reward.pojo.RewardModel;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public interface RewardDetailContract {
    interface View extends BaseView {
        void showRewardDetail(RewardDetailResponseModel rewardModel);
        void showRewardDetailBuy(RewardModel rewardModel);
        void showFullDetail(String detail);
        void setCoin(ModelCoinAndBit modelCoinAndBit);
        void showAddress(int requestCode);
        void showCalculatePrice(Bundle bundle);
        void hideDialogRewardDetailBuy();
    }

    interface Presenter {
        void getRewardDetail();
        void getRewardDetailBuy();
        void getCoin();
        void stopRealTime();
        void getFullDetail();
        void gotoAddress();
        void buyProduct();
        void getActivityResult(int requestCode,Intent data);

    }

}
