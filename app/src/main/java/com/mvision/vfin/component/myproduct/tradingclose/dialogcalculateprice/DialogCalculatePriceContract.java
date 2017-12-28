package com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice;

import com.mvision.vfin.api.response.OrderCalFeeResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.myproduct.tradingclose.pojo.MyproductModel;

/**
 * Created by enter_01 on 12/18/2017 AD.
 */

public interface DialogCalculatePriceContract {
    interface View extends BaseView {
        void showDialogloading();
        void showButtonHaveWallet(int priceProduct,int shippingPrice,int systemFeePrice,
                                  int totalPrice);
        void showButtonNoWallet(int priceProduct,int shippingPrice,int systemFeePrice,
                                int totalPrice);
        void showShipPing(int priceProduct,int shippingPrice,int systemFeePrice,
                                int totalPrice);
        void showCreateOrder(int priceProduct,int shippingPrice,int systemFeePrice,
                             int totalPrice);
         void disDialog();
    }

    interface Presenter {
        void getPrice();
        void getCreateOrder();
        void stopRealTime();
    }
}
