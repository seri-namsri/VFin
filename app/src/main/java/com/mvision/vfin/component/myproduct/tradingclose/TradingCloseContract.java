package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;

import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public interface TradingCloseContract {

    interface View extends BaseView {
        void setUpViewMyproduct(MyProductResponseModel myProductResponseModel);
        void gotoAdddress(int requestCode);
        void gotoBuyProduct();
        void gotoBuyProductFail();
        void gotoSentProduct();
        void setViewUpdateAddress();
    }

    interface Presenter {
        void getMyProduct();
        void getOnclickProduct();
        void getActivityResult(int requestCode, Intent data);

    }
}
