package com.mvision.vfin.component.addeditdress;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddEditAddressPresenter extends Presenter<AddEditAddressContract.View> implements
        AddEditAddressContract.Presenter {

    private AddEditAddressContract.View view;
    private int positionAddress = -1;
    private AddressModel addressModel = null;
    private Province province ;
    private Amphur amphur ;
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        try {
            addressModel = Parcels.unwrap(extras.getParcelable("addressModel"));
            positionAddress = extras.getInt("positionAddress");
            province = addressModel.province;
            amphur = addressModel.amphur;
        } catch (NullPointerException e) {
            addressModel = new AddressModel();
        }
    }

    @Override
    public void getDataResult(int requestCode, Intent data) {
        if (requestCode == 1){
             province = Parcels.unwrap(data.getParcelableExtra("province"));
             view.setClickProvince(province);
             amphur  = null;
            view.setClickAmphur("");
        }else if (requestCode == 2){
            amphur = Parcels.unwrap(data.getParcelableExtra("amphur"));
            view.setClickAmphur(amphur.amphurName);
        }
    }

    public AddEditAddressPresenter(AddEditAddressContract.View view) {
        this.view = view;
    }

    @Override
    public void getProvince() {
        view.showProvince();
    }

    @Override
    public void getDistrict() {
        try {
            view.showDistrict(province.id);
        }catch (NullPointerException e){
            view.showMessageFail("กรุณาเลือกจังหวัดก่อน");
        }
    }

    @Override
    public void getSubDistrict() {

    }

    @Override
    public void sentAddress(final AddressModel addressModel) {
        if (province != null && amphur != null){
            addressModel.setProvince(province);
            addressModel.setAmphur(amphur);
            AddEditAddressManage.getInstance().saveAddress(addressModel, new Query.CallBackData() {
                @Override
                public <T> void onSuccess(T t) {
                    view.saveAddressSuccess((AddressModel) t,positionAddress);
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
