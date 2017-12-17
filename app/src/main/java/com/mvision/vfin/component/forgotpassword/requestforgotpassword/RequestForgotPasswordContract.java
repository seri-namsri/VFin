package com.mvision.vfin.component.forgotpassword.requestforgotpassword;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public interface RequestForgotPasswordContract {

    interface View extends BaseView {
        void sentOtpSuccess();
    }

    interface Presenter {
        void sentOtp();

    }
}
