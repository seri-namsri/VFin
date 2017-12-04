package com.mvision.vfin.component.myaddress;

import android.content.Intent;

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

    public MyAddressPresenter(MyAddressContract.View view) {
        this.view = view;
    }

    @Override
    public void getAddress() {
        MyAddressManage.getInstance().myAddress(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                myAddressResponseModel = (MyAddressResponseModel) t;
                view.setUpViewAddress(myAddressResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getUpdateAddress(int requestCode,Intent intent) {
        AddressModel addressModel = Parcels.unwrap(intent.getParcelableExtra("addressModel"));
        if (requestCode == 1)
        myAddressResponseModel.result.add(addressModel);
        else if (requestCode == 2){
            int position = intent.getIntExtra("positionAddress",-1);
            myAddressResponseModel.result.set(position,addressModel);
        }

        view.updateDataAddress();
    }
}
