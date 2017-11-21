package com.example.enter_01.vfin.component.question;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.question.pojo.QuestionModel;

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
