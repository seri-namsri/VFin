package com.mvision.vfin.component.authen.register;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public interface RegisterContract {

    interface View extends BaseView {
        void setUpViewRegisterByPhone();
        void setUpViewRegisterByEmail();
        void registerSuccess();
    }

    interface Presenter {
        void clickRegisterByFacebook();
        void clickRegisterByEmail();
        void clickRegisterByPhone();
    }

}
