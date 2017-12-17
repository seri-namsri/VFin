package com.mvision.vfin.component.myproduct.tradingclose;

import com.mvision.vfin.api.request.Product;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class TradingCloseManage {

    private static TradingCloseManage instance = null;

    public static TradingCloseManage getInstance() {
        if (instance == null)
            instance = new TradingCloseManage();
        return instance;
    }


    public void getMyProduct(final Query.CallBackData callBackData, final String type){
        RetrofitUtility.getInstance()
            .getRetrofit()
                .create(Product.class).getMyProduct(type,PreferencesMange.getInstance()
                        .getMemberID()
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyProductResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getMyProduct(callBackData,type);
                            }
                        });

                    }

                    @Override
                    public void onNext(MyProductResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }

}
