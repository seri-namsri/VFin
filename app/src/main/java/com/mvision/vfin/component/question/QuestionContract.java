package com.mvision.vfin.component.question;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.question.pojo.QuestionModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public interface QuestionContract {
    interface View extends BaseView {
        void setUpViewQuestion(ArrayList<QuestionModel>questionModelArrayList);
    }

    interface Presenter {
        void getQuestion();

    }
}
