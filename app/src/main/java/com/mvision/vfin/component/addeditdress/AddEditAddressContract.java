package com.mvision.vfin.component.addeditdress;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public interface AddEditAddressContract {
    interface View extends BaseView {
        void showProvince();

        void showDistrict();

        void showSubDistrict();

        void showSetUpEdit(AddressModel addressModel);

        void showSetUpAdd();

        void saveAddressSuccess(AddressModel addressModel,int positionAddress);

        void clickOk(AddressModel addressModel);
    }

    interface Presenter {

        void getProvince();

        void getDistrict(int idProvince);

        void getSubDistrict(int idDistrict);

        void sentAddress(AddressModel addressModel);

        void getEditAddress();

        void getClickOk();
    }
}
