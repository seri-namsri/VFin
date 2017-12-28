package com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword;

import android.os.Bundle;

import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class RequestForgotPasswordPresenter extends Presenter<RequestForgotPasswordContract.View>
        implements RequestForgotPasswordContract.Presenter {

    private RequestForgotPasswordContract.View view;

    public RequestForgotPasswordPresenter(RequestForgotPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void sentOtp() {
        view.sentOtpSuccess();
    }

    @Override
    public void getOtp(final String mobilePhone) {
        if (!mobilePhone.isEmpty()) {
            view.showLoading();
            RequestForgotPasswordManage.getInstance().forgotPassword(new Query.CallBackData() {
                @Override
                public <T> void onSuccess(T t) {
                    view.hideLoading();
                    ForgotPasswordModelRespone forgotPasswordModelRespone = (ForgotPasswordModelRespone) t;
                    Bundle bundle = new Bundle();
                    bundle.putString("mobilePhone",mobilePhone);
                    bundle.putString("refCode",forgotPasswordModelRespone.result.refCode);
                    bundle.putString("otpType","forgotPassword");
                    view.showOtp(bundle);
                }

                @Override
                public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                }

                @Override
                public void onFail(String error) {
                    view.hideLoading();
                }
            }, mobilePhone,"forgotPassword");
        }

    }
}
