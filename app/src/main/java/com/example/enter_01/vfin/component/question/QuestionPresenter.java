package com.example.enter_01.vfin.component.question;

import com.example.enter_01.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class QuestionPresenter extends Presenter<QuestionContract.View> implements
        QuestionContract.Presenter {



    private QuestionContract.View view ;
    public QuestionPresenter(QuestionContract.View view){
        this.view = view;
    }

    @Override
    public void getQuestion() {



    }
}
