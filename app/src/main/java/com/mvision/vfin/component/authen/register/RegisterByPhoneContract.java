package com.mvision.vfin.component.authen.register;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/21/2017 AD.
 */

public interface RegisterByPhoneContract {

    interface View extends BaseView {
        void showRegisterSuccess();
        void verifyAccountFail();
        void gotoVerifyOTP(Bundle bundle,int requestCode);
        void verifyAccountSuccess(ForgotPasswordModelRespone forgotPasswordModelRespone);
    }

    interface Presenter {
        void getRegister();
        void verifyAccount(String mobilePhone,String email,String faceBookId,String password,
                           String newPassword);
        void getActivityResult(int requestCode , Intent data);
    }

}
