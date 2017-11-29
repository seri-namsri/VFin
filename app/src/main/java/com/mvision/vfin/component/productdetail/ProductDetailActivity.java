package com.mvision.vfin.component.productdetail;

import android.support.v4.app.FragmentTransaction;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseActivity;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailActivity extends BaseActivity {
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
        transaction.replace(R.id.container, ProductDetailFragment.newInstance(getIntent().getExtras()));
        transaction.commit();
    }
}
