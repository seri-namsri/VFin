package com.mvision.vfin.component.survey;

import com.mvision.vfin.component.survey.pojo.SurveyModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class SurveyManage {

    private static SurveyManage instance = null;
    public static SurveyManage getInstance() {
        if (instance == null)
            instance = new SurveyManage();
        return instance;
    }


    public void getSurvey(final Query.CallBackData callBackData) {
    }

}
