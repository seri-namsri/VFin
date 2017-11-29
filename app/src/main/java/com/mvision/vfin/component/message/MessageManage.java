package com.mvision.vfin.component.message;

import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageManage {

    private static MessageManage instance = null;
    private static FirebaseFirestore db;

    public static MessageManage getInstance() {
        if (instance == null)
            instance = new MessageManage();
        return instance;
    }

    public void getMessage(final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataCollection(db.collection("message"), new
                MessageData(), new Query.CallBackData() {
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
