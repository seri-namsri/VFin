package com.example.enter_01.vfin.component.productdetail.alertdetail;

import com.example.enter_01.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public interface AlertDetailContract {

    interface View extends BaseView {

        void setUpViewProductDetail(String detail);
    }

    interface Presenter {
        void getProductDetail();


    }

}
