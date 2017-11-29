package com.mvision.vfin.component.authen.login;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public interface LoginContract {

    interface View extends BaseView {

        void setUpViewLogin();

    }

    interface Presenter {
        void getLogin(String tel,String password);
        void getLoginFaceBook(String faceBookId);

    }
}
