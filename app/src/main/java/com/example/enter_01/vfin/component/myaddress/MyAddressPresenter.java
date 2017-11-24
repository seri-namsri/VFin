package com.example.enter_01.vfin.component.myaddress;

import com.example.enter_01.vfin.api.response.MyAddressResponseModel;
import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressPresenter extends Presenter<MyAddressContract.View> implements
        MyAddressContract.Presenter{

    private MyAddressContract.View view ;
    public MyAddressPresenter(MyAddressContract.View view){
        this.view = view;
    }

    @Override
    public void getAddress() {
        MyAddressManage.getInstance().myAddress(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                   view.setUpViewAddress((MyAddressResponseModel) t);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
