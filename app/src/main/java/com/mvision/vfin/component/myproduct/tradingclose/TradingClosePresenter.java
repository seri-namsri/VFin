package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class TradingClosePresenter extends Presenter<TradingCloseContract.View>
        implements TradingCloseContract.Presenter{

    private int requestCodeAddress = 1;
    private TradingCloseContract.View view ;
    private String type ;
    public TradingClosePresenter(TradingCloseContract.View view){
        this.view = view ;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        type = extras.getString("type");
    }

    @Override
    public void getMyProduct() {
        TradingCloseManage.getInstance().getMyProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.setUpViewMyproduct((MyProductResponseModel) t);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        },type);

    }

    @Override
    public void getOnclickProduct() {
        view.gotoAdddress(requestCodeAddress);
    }

    @Override
    public void getActivityResult(int requestCode, Intent data) {
        if (requestCode == requestCodeAddress){

        }
    }
}
