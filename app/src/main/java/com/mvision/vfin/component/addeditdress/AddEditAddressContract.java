package com.mvision.vfin.component.addeditdress;

import android.content.Intent;

import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public interface AddEditAddressContract {
    interface View extends BaseView {
        void showProvince();

        void setClickProvince(Province province);

        void setClickAmphur(String amphur);

        void showDistrict(int idProvince);

        void showSubDistrict();

        void showSetUpEdit(AddressModel addressModel);

        void showSetUpAdd();

        void saveAddressSuccess(AddressModel addressModel,int positionAddress);

        void clickOk(AddressModel addressModel);
    }

    interface Presenter {

        void getProvince();

        void getDistrict();

        void getSubDistrict();

        void sentAddress(AddressModel addressModel);

        void getEditAddress();

        void getClickOk();

        void getDataResult(int requestCode,Intent data);
    }
}
