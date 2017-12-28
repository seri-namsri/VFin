package com.mvision.vfin.component.myaddress;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.firebase.Firestore.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressPresenter extends Presenter<MyAddressContract.View> implements
        MyAddressContract.Presenter {

    private MyAddressContract.View view;
    private MyAddressResponseModel myAddressResponseModel;
    private AddressModel addressModel ;
    public MyAddressPresenter(MyAddressContract.View view) {
        this.view = view;
    }
    public int codeCheck = -1 ;
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        try {
            codeCheck = extras.getInt("code");
        }catch (NullPointerException e){}
    }

    @Override
    public void getAddress() {
        view.showLoading();
        MyAddressManage.getInstance().myAddress(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.hideLoading();
                myAddressResponseModel = (MyAddressResponseModel) t;
                view.setUpViewAddress(myAddressResponseModel);
                addressModel = getFirstAddress(myAddressResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    private AddressModel getFirstAddress(MyAddressResponseModel myAddressResponseModel){
        try {
            for (AddressModel addressModel : myAddressResponseModel.result){
                if ( addressModel.isPrimary.equals("yes")){
                    return addressModel;
                }
            }
        }catch (NullPointerException e){}

     return null;
    }

    @Override
    public void updateAddressIsPrimary() {
        if (codeCheck == -1){
            try {
                MyAddressManage.getInstance().updateAddressIsPrimary(new Query.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {
                        view.updateAddressIsPrimarySuccess(addressModel);
                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                },addressModel.id);
            }catch (NullPointerException e){
                view.updateAddressIsPrimaryFail();
            }
        }else {
            view.updateAddressIsPrimarySuccess(addressModel);
        }


    }

    @Override
    public void getUpdateAddress(int requestCode,Intent intent) {
        AddressModel addressModel = Parcels.unwrap(intent.getParcelableExtra("addressModel"));
        if (requestCode == 1){
            if (myAddressResponseModel.result == null)
                myAddressResponseModel.result = new ArrayList<>();
            myAddressResponseModel.result.add(0,addressModel);
        }

        else if (requestCode == 2){
            int position = intent.getIntExtra("positionAddress",-1);
            myAddressResponseModel.result.set(position,addressModel);
            this.addressModel  = addressModel;
        }

        view.updateDataAddress();
    }


    @Override
    public void clickAddress(AddressModel addressModel) {
        this.addressModel = addressModel ;
    }
}
