package com.mvision.vfin.component.adddress;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.adddress.model.AddressModel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddAddressPresenter extends Presenter<AddAddressContract.View> implements
        AddAddressContract.Presenter {

    private AddAddressContract.View view ;
    public AddAddressPresenter(AddAddressContract.View view){
        this.view = view;
    }

    @Override
    public void getProvince() {

    }

    @Override
    public void getDistrict(int idProvince) {

    }

    @Override
    public void getSubDistrict(int idDistrict) {

    }

    @Override
    public void sentAddress(AddressModel addressModel) {

    }
}
