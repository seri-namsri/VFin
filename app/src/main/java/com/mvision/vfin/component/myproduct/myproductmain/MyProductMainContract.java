package com.mvision.vfin.component.myproduct.myproductmain;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface MyProductMainContract {

    interface View extends BaseView {
        void setUpView(int changeView);
    }

    interface Presenter {
        void getFirstView();

    }
}
