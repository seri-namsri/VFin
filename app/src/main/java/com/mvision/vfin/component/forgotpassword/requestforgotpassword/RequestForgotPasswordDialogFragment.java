package com.mvision.vfin.component.forgotpassword.requestforgotpassword;

import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseDialogFragment;
import com.mvision.vfin.component.forgotpassword.validateotp.ValidateOtpActivity;

import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class RequestForgotPasswordDialogFragment extends BaseDialogFragment implements
        RequestForgotPasswordContract.View{

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

    }

    @Override
    public void hideLoading() {

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
        startActivityFromFragment(ValidateOtpActivity.class,null);
    }

    @OnClick({R.id.buttonOk,R.id.buttonCancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonOk :
                presenter.sentOtp();
                break;
            case R.id.buttonCancel :
                dismiss();
                break;
        }
    }


}
