package com.mvision.vfin.component.survey;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.survey.pojo.SurveyModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class SurveyPresenter extends Presenter<SurveyContract.View> implements SurveyContract.Presenter{

    private SurveyContract.View view ;
    public SurveyPresenter(SurveyContract.View view){
        this.view = view;
    }

    @Override
    public void getSurvey() {
        SurveyManage.getInstance().getSurvey(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                 view.setUpViewSurvey((ArrayList<SurveyModel>) tArrayList);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
