package com.mvision.vfin.component.rewarddetail;

import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailManage {

    private static RewardDetailManage instance = null;
    public static RewardDetailManage getInstance() {
        if (instance == null)
            instance = new RewardDetailManage();
        return instance;
    }



}
