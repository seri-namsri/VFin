package com.mvision.vfin.component.authen.register;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.register.email.RegisterByEmailFragment;
import com.mvision.vfin.component.authen.register.phone.RegisterByPhoneFragment;
import com.mvision.vfin.utility.Log;

import butterknife.OnClick;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public class RegisterFragment extends BaseFragment {

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
    }

    @Override
    protected void startView() {
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_register_main;
    }


    @OnClick({R.id.buttonRegisterByPhone})
    public void buttonRegisterByPhone(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, RegisterByPhoneFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick({R.id.buttonRegisterByEmail})
    public void buttonRegisterByEmail(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, RegisterByEmailFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick({R.id.buttonRegisterByFacebook})
    public void buttonRegisterByFacebook(View view) {
    }
}
