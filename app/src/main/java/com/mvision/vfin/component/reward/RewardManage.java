package com.mvision.vfin.component.reward;

import com.mvision.vfin.component.reward.pojo.*;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class RewardManage {
    private static RewardManage instance = null;
    private static FirebaseFirestore db ;
    public static RewardManage getInstance() {
        if (instance == null)
            instance = new RewardManage();
        return instance;
    }


    public void getRewardByCat(final Query.CallBackData callBackData,String catId){
        db = FirebaseFirestore.getInstance();

        Query.getInstance().readDataCollection( db.collection("reward").whereEqualTo("cat_id",catId),new
                RewardModel(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                callBackData.onSuccess(t);

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                callBackData.onSuccessAll(tArrayList);
            }

            @Override
            public void onFail(String error) {
            }
        });
    }

    public void getCagegory(final Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();

        Query.getInstance().readDataCollection( db.collection("category")
                ,new
                CategoryModel(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                callBackData.onSuccess(t);

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                callBackData.onSuccessAll(tArrayList);
            }

            @Override
            public void onFail(String error) {
            }
        });
    }
}
