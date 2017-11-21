package com.example.enter_01.vfin.component.rewarddetail;

import com.example.enter_01.vfin.component.reward.pojo.RewardModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailManage {

    private static RewardDetailManage instance = null;
    private static FirebaseFirestore db ;
    public static RewardDetailManage getInstance() {
        if (instance == null)
            instance = new RewardDetailManage();
        return instance;
    }


    public void getRewardDetail(String rewardId, Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocument(db.document("reward/"+rewardId), new RewardModel(),
                callBackData);
    }


}
