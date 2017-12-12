package com.mvision.vfin.component.myaddress;

import android.content.Intent;

import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface MyAddressContract {
    interface View extends BaseView {

        void setUpViewAddress(MyAddressResponseModel myAddressResponseModel);
        void updateDataAddress();
        void updateAddressIsPrimarySuccess(AddressModel addressModel);
        void updateAddressIsPrimaryFail();
    }

    interface Presenter {
        void getAddress();
        void updateAddressIsPrimary();
        void getUpdateAddress(int requestCode,Intent intent);
        void clickAddress(AddressModel addressModel);

    }
}
