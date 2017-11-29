package com.mvision.vfin.component.survey;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.question.QuestionActivity;
import com.mvision.vfin.component.survey.pojo.SurveyModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class SurveyFragment extends BaseFragment implements SurveyContract.View{

    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    private SurveyPresenter presenter ;

    public static  SurveyFragment newInstance(){
        SurveyFragment fragment = new SurveyFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializePresenter() {
        presenter = new SurveyPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getSurvey();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_survey;
    }

    @Override
    public void setUpViewSurvey(ArrayList<SurveyModel> surveyModelArrayList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        SurveyAdapter surveyAdapter = new SurveyAdapter(surveyModelArrayList, new SurveyAdapter.CallBackClick() {
            @Override
            public void clickItemProduct(SurveyModel surveyModel) {
                 startActivityFromFragment(QuestionActivity.class,null);
            }
        });
        recyclerView.setAdapter(surveyAdapter);
    }
}
