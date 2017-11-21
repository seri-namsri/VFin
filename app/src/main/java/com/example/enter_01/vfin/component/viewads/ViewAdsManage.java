package com.example.enter_01.vfin.component.viewads;

import com.example.enter_01.vfin.component.viewads.pojo.ViewAdsModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsManage {

    private static ViewAdsManage instance = null;
    private static FirebaseFirestore db ;
    public static ViewAdsManage getInstance() {
        if (instance == null)
            instance = new ViewAdsManage();
        return instance;
    }


    public void getAds(final Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();

        Query.getInstance().readDataCollection( db.collection("ads"),new
                ViewAdsModel(), new Query.CallBackData() {
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
