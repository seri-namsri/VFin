package com.example.enter_01.vfin.component.financehistory;

import com.example.enter_01.vfin.component.financehistory.pojo.FinanceHistoryModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryManage {
    private static FinanceHistoryManage instance = null;
    private static FirebaseFirestore db;

    public static FinanceHistoryManage getInstance() {
        if (instance == null)
            instance = new FinanceHistoryManage();
        return instance;
    }


    public void getFinanceHistory(final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataCollection(db.collection("finance"), new
                FinanceHistoryModel(), new Query.CallBackData() {
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
