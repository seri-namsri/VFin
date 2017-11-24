package com.example.enter_01.vfin.component.myaddress;

import com.example.enter_01.vfin.api.response.MyAddressResponseModel;
import com.example.enter_01.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface MyAddressContract {
    interface View extends BaseView {

        void setUpViewAddress(MyAddressResponseModel myAddressResponseModel);
    }

    interface Presenter {
        void getAddress();

    }
}
