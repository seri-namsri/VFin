package com.mvision.vfin.component.authen.register.phone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.forgotpassword.validateotp.ValidateOtpActivity;
import com.mvision.vfin.component.authen.register.RegisterByPhoneContract;
import com.mvision.vfin.component.authen.register.RegisterByPhonePresenter;
import com.mvision.vfin.customview.EditTextWithFont;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public class RegisterByPhoneFragment extends BaseFragment implements RegisterByPhoneContract.View{

    @BindView(R.id.editTextPhone)
    EditTextWithFont editTextPhone;
    @BindView(R.id.editTextPassword)
    EditTextWithFont editTextPassword;
    @BindView(R.id.editTextConfirmPassword)
    EditTextWithFont editTextConfirmPassword;

    private RegisterByPhonePresenter presenter ;

    public static RegisterByPhoneFragment newInstance() {
        RegisterByPhoneFragment fragment = new RegisterByPhoneFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {
       showProgress(getString(R.string.textLoading));
    }

    @Override
    public void hideLoading() {
       dismissProgress();
    }

    @Override
    protected void initializePresenter() {
        presenter = new RegisterByPhonePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_register_by_phone;
    }

    @OnClick({R.id.buttonOk})
    public void buttonOk(View view){
        presenter.verifyAccount(editTextPhone.getTextDataNotNull(null)
                ,null,null,editTextPassword.getTextDataNotNull(null),editTextConfirmPassword
                        .getTextDataNotNull(null));
    }

    @Override
    public void showRegisterSuccess() {
        getActivity().finish();
    }

    @Override
    public void verifyAccountFail() {

    }

    @Override
    public void gotoVerifyOTP(Bundle bundle,int requestCode) {
        startActivityResultFromFragment(ValidateOtpActivity.class,bundle,requestCode);
    }

    @Override
    public void verifyAccountSuccess(ForgotPasswordModelRespone forgotPasswordModelRespone) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getActivityResult(requestCode,data);
    }
}
