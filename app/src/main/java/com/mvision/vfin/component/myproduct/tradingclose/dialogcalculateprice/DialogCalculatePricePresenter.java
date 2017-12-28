package com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice;

import android.os.Bundle;

import com.google.gson.Gson;
import com.mvision.vfin.api.response.OrderCalFeeResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.myproduct.tradingclose.pojo.MyproductModel;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.component.splashscreen.dialoglanguage.DialogLanguagePresenter;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/18/2017 AD.
 */

public class DialogCalculatePricePresenter extends Presenter<DialogCalculatePriceContract.View>
        implements DialogCalculatePriceContract.Presenter {

    private DialogCalculatePriceContract.View view;
    private AddressModel addressModel;
    private MyproductModel myproductModel;
    private ModelCoinAndBit modelCoinAndBit;
    private OrderCalFeeResponseModel orderCalFeeResponseModel;

    private RewardModel rewardModel;

    public DialogCalculatePricePresenter(DialogCalculatePriceContract.View view) {
        this.view = view;
        getCoin();
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        addressModel = Parcels.unwrap(extras.getParcelable("addressModel"));
        myproductModel = Parcels.unwrap(extras.getParcelable("myproductModel"));
        rewardModel = Parcels.unwrap(extras.getParcelable("rewardModel"));
    }

    private void getCoin() {
        DialogCalculatePriceManage.getInstance().getCoin(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                modelCoinAndBit = (ModelCoinAndBit) t;

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
    public void getPrice() {
        String codePrice;
        try {
            codePrice = myproductModel.productOnShelfCode;

        } catch (NullPointerException e) {

            codePrice = rewardModel.code;
        }
        DialogCalculatePriceManage.getInstance().getOrderCalFee(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                orderCalFeeResponseModel = (OrderCalFeeResponseModel) t;
                checkDataPrice(myproductModel, orderCalFeeResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        }, codePrice, addressModel);

        // view.showButtonNoWallet();
    }


    private int shippingPrice;
    private int systemFeePrice;
    private int productPrice;

    private void checkDataPrice(MyproductModel myproductModel, OrderCalFeeResponseModel orderCalFeeResponseModel) {

        shippingPrice = orderCalFeeResponseModel.result
                .shippingPrice;
        systemFeePrice = orderCalFeeResponseModel.result
                .systemFeePrice;
        int totalPrice;
        try {
            productPrice = myproductModel.currentPrice;
            totalPrice = shippingPrice + systemFeePrice;
            productPrice = 0;

        } catch (NullPointerException e) {
            productPrice = rewardModel
                    .currentPrice;
            totalPrice = productPrice + shippingPrice + systemFeePrice;

        }
        if (totalPrice <= modelCoinAndBit.getWallet()) {
            if (productPrice > 0)
                view.showButtonHaveWallet(productPrice, shippingPrice, systemFeePrice, totalPrice);
            else {
                view.showShipPing(productPrice, shippingPrice, systemFeePrice, totalPrice);
            }
        } else {
            view.showButtonNoWallet(productPrice, shippingPrice, systemFeePrice, totalPrice);
        }


    }

    @Override
    public void getCreateOrder() {
        view.showDialogloading();
        try {
            DialogCalculatePriceManage.getInstance().getCreateOrder(callBackData, myproductModel.code, addressModel, shippingPrice, systemFeePrice);
        } catch (NullPointerException e) {
            DialogCalculatePriceManage.getInstance().getBuyProduct(callBackData, rewardModel.code, addressModel, shippingPrice, systemFeePrice, rewardModel.currentPrice);
        }

    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
            try {
                productPrice = myproductModel
                        .currentPrice;
            } catch (NullPointerException e) {
                productPrice = rewardModel
                        .currentPrice;
            }

            shippingPrice = orderCalFeeResponseModel.result
                    .shippingPrice;
            systemFeePrice = orderCalFeeResponseModel.result
                    .systemFeePrice;

            int totalPrice = productPrice + shippingPrice + systemFeePrice;

            view.showCreateOrder(productPrice, shippingPrice, systemFeePrice, totalPrice);
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {
            view.disDialog();
        }
    };

    @Override
    public void stopRealTime() {
        DialogCalculatePriceManage.getInstance().onStopRealTime();
    }
}
