package com.mvision.vfin.component.authen.forgotpassword.validateotp;

import android.os.Bundle;

import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword.RequestForgotPasswordManage;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ValidateOtpPresenter extends Presenter<ValidateOtpContract.View> implements
        ValidateOtpContract.Presenter {

    private ValidateOtpContract.View view;
    private String mobilePhone;
    private String refCode;
    private String otpType;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        mobilePhone = extras.getString("mobilePhone");
        refCode = extras.getString("refCode");
        otpType = extras.getString("otpType");
    }

    public ValidateOtpPresenter(ValidateOtpContract.View view) {
        this.view = view;
    }

    @Override
    public void verifyOtp(final String otpCode) {
        view.showLoading();
        final Bundle bundle = new Bundle();
        bundle.putString("mobilePhone", mobilePhone);
        bundle.putString("refCode", refCode);
        bundle.putString("otpCode", otpCode);
        if (otpType.equals("forgotPassword")) {

            ValidateOtpManage.getInstance().validateOtp(new Query.CallBackData() {
                @Override
                public <T> void onSuccess(T t) {
                    view.hideLoading();
                    view.verifySuccess(bundle);
                }

                @Override
                public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                }

                @Override
                public void onFail(String error) {
                    view.hideLoading();
                }
            }, mobilePhone, otpCode, refCode);

        } else if (otpType.equals("register")) {
            view.verifyRegisterSuccess(bundle);
        }
    }

    @Override
    public void getRefCode() {
        view.showRefCode(refCode);
    }

    @Override
    public void getResentOtp() {
        view.showLoading();
        RequestForgotPasswordManage.getInstance().forgotPassword(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.hideLoading();
                ForgotPasswordModelRespone forgotPasswordModelRespone = (ForgotPasswordModelRespone) t;
                refCode = forgotPasswordModelRespone.result.refCode;
                view.showRefCode(refCode);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.hideLoading();
            }
        }, mobilePhone, otpType);
    }
}
