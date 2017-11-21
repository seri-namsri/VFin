package com.example.enter_01.vfin.component.productdetail;

import android.support.v4.app.FragmentTransaction;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseActivity;
import com.example.enter_01.vfin.component.messagedetail.MessageDetailFragment;

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
