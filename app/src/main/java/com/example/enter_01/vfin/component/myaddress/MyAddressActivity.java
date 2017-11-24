package com.example.enter_01.vfin.component.myaddress;

import android.support.v4.app.FragmentTransaction;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseActivity;
import com.example.enter_01.vfin.component.messagedetail.MessageDetailFragment;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressActivity extends BaseActivity {
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
        transaction.replace(R.id.container, MyAddressFragment.newInstance());
        transaction.commit();
    }
}
