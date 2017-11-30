package com.mvision.vfin.component.adddress;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.adddress.model.AddressModel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public interface AddAddressContract {
    interface View extends BaseView {
        void showProvince();

        void showDistrict();

        void showSubDistrict();
    }

    interface Presenter {

        void getProvince();

        void getDistrict(int idProvince);

        void getSubDistrict(int idDistrict);

        void sentAddress(AddressModel addressModel);
    }
}
