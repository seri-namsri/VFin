package com.mvision.vfin.component.forgotpassword.confrimpassword;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public interface ConfirmPasswordContract {
    interface View extends BaseView {
        void setChangePasswordSuccess();
    }

    interface Presenter {
        void sentChangePassword();

    }
}
