package com.mvision.vfin.component.leveldetail;

import android.support.v4.app.FragmentTransaction;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseActivity;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class LevelDetailActivity extends BaseActivity {
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
    protected int setLayoutResourceIdentifier() {
        return R.layout.activity_base;
    }



    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void startView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, LevelDetailFragment.newInstance());
        transaction.commit();
    }
}
