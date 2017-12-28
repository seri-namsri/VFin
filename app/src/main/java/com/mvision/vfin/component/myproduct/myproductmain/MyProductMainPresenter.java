package com.mvision.vfin.component.myproduct.myproductmain;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductMainPresenter extends Presenter<MyProductMainContract.View> implements
        MyProductMainContract.Presenter{

    public MyProductMainContract.View view ;
    private int changePage = 0;
    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        try {
            changePage = extras.getInt("changePage");

        }catch (NullPointerException e){}
    }

    public MyProductMainPresenter(MyProductMainContract.View view){
        this.view = view;
    }

    @Override
    public void getFirstView() {
        view.setUpView(changePage);
    }
}
