package com.example.enter_01.vfin.component.survey;

import com.example.enter_01.vfin.component.survey.pojo.SurveyModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class SurveyManage {

    private static SurveyManage instance = null;
    private static FirebaseFirestore db ;
    public static SurveyManage getInstance() {
        if (instance == null)
            instance = new SurveyManage();
        return instance;
    }


    public void getSurvey(final Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();

        Query.getInstance().readDataCollection( db.collection("survey"),new
                SurveyModel(), new Query.CallBackData() {
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
