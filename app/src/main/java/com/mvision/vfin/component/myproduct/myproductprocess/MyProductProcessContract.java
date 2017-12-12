package com.mvision.vfin.component.myproduct.myproductprocess;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface MyProductProcessContract {

    interface View extends BaseView {
        void setUpViewMyproduct();
    }

    interface Presenter {
        void getMyProduct();
    }
}
