package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface TradingCloseContract {

    interface View extends BaseView {
        void setUpViewMyproduct(MyProductResponseModel myProductResponseModel);
        void gotoAdddress(int requestCode);
        void showDialogCalculatePrice(Bundle bundle);
        void notFoundData();
    }

    interface Presenter {
        void getMyProduct();
        void getOnclickProduct(int position);
        void getActivityResult(int requestCode, Intent data);
    }
}
