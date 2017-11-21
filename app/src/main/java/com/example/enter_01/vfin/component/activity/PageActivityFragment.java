package com.example.enter_01.vfin.component.activity;

import android.view.View;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;
import com.example.enter_01.vfin.component.downloadapp.DownloadAppActivity;
import com.example.enter_01.vfin.component.survey.SurveyActivity;
import com.example.enter_01.vfin.component.viewads.ViewAdsActivity;
import com.example.enter_01.vfin.component.work.WorkActivity;

import butterknife.OnClick;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class PageActivityFragment extends BaseFragment {


    public static PageActivityFragment newInstance() {
        PageActivityFragment fragment = new PageActivityFragment();
        return fragment;
    }


    @OnClick({R.id.buttonDownloadApp, R.id.buttonViewAds, R.id.buttonWork, R.id.buttonTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDownloadApp:
            startActivityFromFragment(DownloadAppActivity.class,null);
                break;
            case R.id.buttonViewAds:
                startActivityFromFragment(ViewAdsActivity.class,null);
                break;
            case R.id.buttonWork:
                startActivityFromFragment(WorkActivity.class,null);
                break;
            case R.id.buttonTest:
                startActivityFromFragment(SurveyActivity.class,null);
                break;
        }
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

    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_page_activity;
    }
}
