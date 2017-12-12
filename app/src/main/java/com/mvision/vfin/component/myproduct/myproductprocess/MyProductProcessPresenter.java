package com.mvision.vfin.component.myproduct.myproductprocess;

import com.mvision.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductProcessPresenter extends Presenter<MyProductProcessContract.View>
        implements MyProductProcessContract.Presenter{

    private MyProductProcessContract.View view ;
    public MyProductProcessPresenter(MyProductProcessContract.View view){
        this.view = view ;
    }

    @Override
    public void getMyProduct() {
          view.setUpViewMyproduct();
    }
}
