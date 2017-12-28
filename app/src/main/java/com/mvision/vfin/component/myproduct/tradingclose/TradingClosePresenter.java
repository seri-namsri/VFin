package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.myproduct.tradingclose.pojo.MyproductModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class TradingClosePresenter extends Presenter<TradingCloseContract.View>
        implements TradingCloseContract.Presenter {

    private int requestCodeAddress = 1;
    private TradingCloseContract.View view;
    private String type;
    private MyproductModel myproductModel;

    public TradingClosePresenter(TradingCloseContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        type = extras.getString("type");
    }

    private MyProductResponseModel myProductResponseModel;

    @Override
    public void getMyProduct() {
        view.showLoading();
        TradingCloseManage.getInstance().getMyProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.hideLoading();
                myProductResponseModel = (MyProductResponseModel) t;
                if (myProductResponseModel.result != null && myProductResponseModel.result.size() > 0)
                    view.setUpViewMyproduct(myProductResponseModel);
                else
                    view.notFoundData();
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.hideLoading();
            }
        }, type);

    }

    @Override
    public void getOnclickProduct(int position) {
        myproductModel = myProductResponseModel.result.get(position);
        Log.e("DialogCalculatePricePresenter009",new Gson().toJson(myproductModel));
        view.gotoAdddress(requestCodeAddress);
    }

    @Override
    public void getActivityResult(int requestCode, Intent data) {
        if (requestCode == requestCodeAddress) {
            try {
                AddressModel  addressModel = Parcels.unwrap(data.getExtras().getParcelable
                        ("addressModel"));
                addressModel.getAddress();
                Bundle bundle = data.getExtras();
                bundle.putParcelable("myproductModel", Parcels.wrap(myproductModel));
                view.showDialogCalculatePrice(bundle);
            } catch (NullPointerException e) {
              //  e.printStackTrace();
            }
        }
    }
}
