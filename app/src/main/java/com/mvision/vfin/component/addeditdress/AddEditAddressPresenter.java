package com.mvision.vfin.component.addeditdress;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.firebase.Firestore.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddEditAddressPresenter extends Presenter<AddEditAddressContract.View> implements
        AddEditAddressContract.Presenter {

    private AddEditAddressContract.View view;
    private int positionAddress = -1;
    AddressModel addressModel = null;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        try {
            addressModel = Parcels.unwrap(extras.getParcelable("addressModel"));
            positionAddress = extras.getInt("positionAddress");
        } catch (NullPointerException e) {
            addressModel = new AddressModel();
        }
    }

    public AddEditAddressPresenter(AddEditAddressContract.View view) {
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
    public void sentAddress(final AddressModel addressModel) {
        AddEditAddressManage.getInstance().saveAddress(addressModel, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.saveAddressSuccess(addressModel,positionAddress);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.showMessageFail(error);
            }
        });
    }

    @Override
    public void getEditAddress() {
        if (addressModel != null && positionAddress >= 0) {
            view.showSetUpEdit(addressModel);
        }else {
            view.showSetUpAdd();
        }
    }



    @Override
    public void getClickOk() {
        view.clickOk(addressModel);
    }
}
