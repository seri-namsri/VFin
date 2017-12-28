package com.mvision.vfin.component.authen.forgotpassword.validateotp;

import android.os.Bundle;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public interface ValidateOtpContract {
    interface View extends BaseView {
        void verifySuccess(Bundle bundle);
        void verifyRegisterSuccess(Bundle bundle);
        void showRefCode(String refCode);
    }

    interface Presenter {
        void verifyOtp(String otpCode);
        void getRefCode();
        void getResentOtp();
    }
}
