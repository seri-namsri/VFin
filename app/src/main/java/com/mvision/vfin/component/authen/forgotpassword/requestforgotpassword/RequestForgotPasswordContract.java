package com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword;

import android.os.Bundle;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public interface RequestForgotPasswordContract {

    interface View extends BaseView {
        void sentOtpSuccess();
        void showOtp(Bundle bundle);
    }

    interface Presenter {
        void sentOtp();
        void getOtp(String mobilePhone);

    }
}
