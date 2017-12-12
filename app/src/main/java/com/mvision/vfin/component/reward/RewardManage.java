package com.mvision.vfin.component.reward;

import com.mvision.vfin.component.reward.pojo.*;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class RewardManage {
    private static RewardManage instance = null;
    public static RewardManage getInstance() {
        if (instance == null)
            instance = new RewardManage();
        return instance;
    }

}
