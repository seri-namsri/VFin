package com.example.enter_01.vfin.component.productdetail.alertdetail;

import android.os.Bundle;

import com.example.enter_01.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class AlertDetailPresenter extends Presenter<AlertDetailContract.View> implements
        AlertDetailContract.Presenter {

    private AlertDetailContract.View view ;
    private String detail;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        detail = extras.getString("product_detail");
    }

    public AlertDetailPresenter(AlertDetailContract.View view){
        this.view = view;
    }

    @Override
    public void getProductDetail() {
        view.setUpViewProductDetail(detail);
    }
}
