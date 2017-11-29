package com.mvision.vfin.component.survey;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.survey.pojo.SurveyModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public interface SurveyContract {

    interface View extends BaseView {
        void setUpViewSurvey(ArrayList<SurveyModel>surveyModelArrayList);
    }

    interface Presenter {
        void getSurvey();

    }

}
