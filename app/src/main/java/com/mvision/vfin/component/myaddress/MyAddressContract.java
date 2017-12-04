package com.mvision.vfin.component.myaddress;

import android.content.Intent;

import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface MyAddressContract {
    interface View extends BaseView {

        void setUpViewAddress(MyAddressResponseModel myAddressResponseModel);
        void updateDataAddress();
    }

    interface Presenter {
        void getAddress();
        void getUpdateAddress(int requestCode,Intent intent);

    }
}
