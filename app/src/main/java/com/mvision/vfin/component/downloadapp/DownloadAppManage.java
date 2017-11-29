package com.mvision.vfin.component.downloadapp;

import com.mvision.vfin.component.downloadapp.pojo.DownloadAppModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/9/2017 AD.
 */

public class DownloadAppManage {


    private static DownloadAppManage instance = null;
    private static FirebaseFirestore db;

    public static DownloadAppManage getInstance() {
        if (instance == null)
            instance = new DownloadAppManage();
        return instance;
    }

    public void getApplication(final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataCollection(db.collection("application"), new
                DownloadAppModel(), new Query.CallBackData() {
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
                callBackData.onFail(error);
            }
        });
    }
}
