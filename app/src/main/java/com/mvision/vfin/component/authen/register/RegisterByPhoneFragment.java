package com.mvision.vfin.component.authen.register;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public class RegisterByPhoneFragment extends BaseFragment {

    public static RegisterByPhoneFragment newInstance() {
        RegisterByPhoneFragment fragment = new RegisterByPhoneFragment();
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
        return R.layout.fragment_register_by_phone;
    }
}
