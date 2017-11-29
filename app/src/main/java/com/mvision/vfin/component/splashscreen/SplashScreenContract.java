package com.mvision.vfin.component.splashscreen;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public interface SplashScreenContract {
    interface View extends BaseView {

        void showMainActivity();

        void showLoginActivity();
    }

    interface Presenter {
        void getAppFirst();

    }

}
