package com.mvision.vfin.component.authen.register;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.RegisterRequest;
import com.mvision.vfin.api.modelrequest.VerifyAccountRequest;
import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/21/2017 AD.
 */

public class RegisterByPhonePresenter extends Presenter<RegisterByPhoneContract.View> implements
        RegisterByPhoneContract.Presenter {

    private RegisterByPhoneContract.View view;
    private String mobilePhone, refCode, otpType, otpCode;
    private String password;

    public RegisterByPhonePresenter(RegisterByPhoneContract.View view) {
        this.view = view;
    }

    @Override
    public void getRegister() {

    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);

    }

    private String email ;
    @Override
    public void verifyAccount(final String mobilePhone, String email, String faceBookId, String password,
                              String newPassword) {
        if (!mobilePhone.isEmpty() && !password.isEmpty() && !newPassword.isEmpty()) {
            if (password.equals(newPassword)){
                view.showLoading();
                this.password = password;
                this.email = email;
                VerifyAccountRequest verifyAccountRequest = new VerifyAccountRequest(mobilePhone, email, faceBookId);
                RegisterManage.getInstance().verifyAccount(new Query.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {
                        view.hideLoading();
                        ForgotPasswordModelRespone forgotPasswordModelRespone = (ForgotPasswordModelRespone) t;
                        Bundle bundle = new Bundle();
                        bundle.putString("refCode", forgotPasswordModelRespone.result.refCode);
                        bundle.putString("mobilePhone", mobilePhone);
                        bundle.putString("otpType", "register");
                        view.gotoVerifyOTP(bundle, 1);
                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {
                        view.hideLoading();
                    }
                }, verifyAccountRequest);
            }else {
                view.showMessageFail(Contextor.getInstance().getContext().getString(R.string.textLoading));
            }

        }

    }

    @Override
    public void getActivityResult(int requestCode, Intent data) {
        if (requestCode == 1) {
            register(data);
        }
    }

    private void register(Intent data) {
        mobilePhone = data.getExtras().getString("mobilePhone");
        refCode = data.getExtras().getString("refCode");
        otpType = data.getExtras().getString("otpType");
        otpCode = data.getExtras().getString("otpCode");
        RegisterRequest registerRequest = new RegisterRequest(email, null, mobilePhone, otpCode,
                password, refCode);
        RegisterManage.getInstance().register(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.showRegisterSuccess();
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        }, registerRequest);
    }
}
