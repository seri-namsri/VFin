package com.mvision.vfin.component.authen.forgotpassword.confrimpassword;

import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ConfirmPasswordPresenter extends Presenter<ConfirmPasswordContract.View> implements
        ConfirmPasswordContract.Presenter {

    private ConfirmPasswordContract.View view;
    private String mobilePhone, otpCode, refCode;

    public ConfirmPasswordPresenter(ConfirmPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        mobilePhone = extras.getString("mobilePhone");
        otpCode = extras.getString("otpCode");
        refCode = extras.getString("refCode");
    }

    @Override
    public void sentChangePassword(String password, String confirmPassword) {
        if (password.isEmpty() || confirmPassword.isEmpty()){
            view.showMessageFail(Contextor.getInstance().getContext().getString(R.string.text_error_input_data_emty));
        }else if (password.equals(confirmPassword)){
            view.showLoading();
            ConfirmPassWordManage.getInstance().upDateNewPassword(new Query.CallBackData() {
                @Override
                public <T> void onSuccess(T t) {
                    view.hideLoading();
                    view.setChangePasswordSuccess();
                }

                @Override
                public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                }

                @Override
                public void onFail(String error) {
                    view.hideLoading();
                }
            }, mobilePhone, otpCode, refCode, password);
        }else {
            view.showMessageFail("รหัสผ่านไม่ตรงกัน");
        }

    }
}
