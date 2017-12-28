package com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseDialogFragment;
import com.mvision.vfin.component.authen.forgotpassword.validateotp.ValidateOtpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class RequestForgotPasswordDialogFragment extends BaseDialogFragment implements
        RequestForgotPasswordContract.View{
    @BindView(R.id.editTextMobilePhone)EditText editTextMobilePhone ;
    private RequestForgotPasswordPresenter presenter ;
    public static RequestForgotPasswordDialogFragment newInstance() {
        RequestForgotPasswordDialogFragment frag = new RequestForgotPasswordDialogFragment();
        return frag;
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
        presenter = new RequestForgotPasswordPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.dialog_fragment_forgot_password;
    }

    @Override
    public void sentOtpSuccess() {
        dismiss();
        startActivityFromFragment(ValidateOtpActivity.class,null);
    }

    @Override
    public void showOtp(Bundle bundle) {
        dismiss();
        startActivityFromFragment(ValidateOtpActivity.class,bundle);
    }

    @OnClick({R.id.buttonOk,R.id.buttonCancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonOk :
                presenter.getOtp(editTextMobilePhone.getText().toString());
                break;
            case R.id.buttonCancel :
                dismiss();
                break;
        }
    }


}
