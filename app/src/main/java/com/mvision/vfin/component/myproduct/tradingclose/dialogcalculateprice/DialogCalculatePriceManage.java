package com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.BuyProductRequest;
import com.mvision.vfin.api.modelrequest.CreateOrderRequest;
import com.mvision.vfin.api.request.Oreder;
import com.mvision.vfin.api.request.Product;
import com.mvision.vfin.api.response.CreateOrderResponseModel;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.api.response.OrderCalFeeResponseModel;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/18/2017 AD.
 */

public class DialogCalculatePriceManage {

    private static DialogCalculatePriceManage instance = null;

    public static DialogCalculatePriceManage getInstance() {
        if (instance == null)
            instance = new DialogCalculatePriceManage();
        return instance;
    }

    public void getOrderCalFee(final Query.CallBackData callBackData,final String code, final AddressModel addressModel){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Oreder.class).getOrderCalFee(GetKey.getInstance()
                .apiCalFee(GetKey
                        .getInstance()
                        .getSignatures())+code,addressModel.getAddress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderCalFeeResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getOrderCalFee(callBackData,code,addressModel);
                            }

                            @Override
                            public void clickCancel() {
                               callBackData.onFail("");
                            }
                        });
                    }
                    @Override
                    public void onNext(OrderCalFeeResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }


    public void getCreateOrder(final Query.CallBackData callBackData, final String code, final
    AddressModel addressModel, final int shippingPrice, final int systemFeePrice){
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(addressModel.getAddress
                (),code,shippingPrice,systemFeePrice);
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Oreder.class).createOrder(GetKey.getInstance()
                .apiCreateOrder(GetKey
                        .getInstance()
                        .getSignatures()),createOrderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CreateOrderResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getCreateOrder(callBackData,code,addressModel,shippingPrice,systemFeePrice);
                            }

                            @Override
                            public void clickCancel() {
                                callBackData.onFail("");
                            }
                        });
                    }
                    @Override
                    public void onNext(CreateOrderResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }


    public void getBuyProduct(final Query.CallBackData callBackData, final String code, final
    AddressModel addressModel, final int shippingPrice, final int systemFeePrice, final int price){
        BuyProductRequest buyProductRequest = new BuyProductRequest(addressModel.getAddress
                (),code,shippingPrice,systemFeePrice,price);
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Oreder.class).buyPruduct(GetKey.getInstance()
                .apiTradeBuy(GetKey
                        .getInstance()
                        .getSignatures()),buyProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CreateOrderResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getBuyProduct(callBackData,code,addressModel,shippingPrice,
                                        systemFeePrice,price);
                            }

                            @Override
                            public void clickCancel() {
                                callBackData.onFail("");
                            }
                        });
                    }
                    @Override
                    public void onNext(CreateOrderResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }



    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRefCoins;
    private ValueEventListener valueEventListener ;

    public void getCoin(final Query.CallBackData callBackData){
        myRefCoins= database.getReference("walletAndAbility/" + PreferencesMange
                .getInstance().getMemberID());
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Gson gson = new Gson();
                ModelCoinAndBit modelCoinAndBit = gson.fromJson(gson.toJson(dataSnapshot.getValue
                        ()),ModelCoinAndBit.class);
                callBackData.onSuccess(modelCoinAndBit);
                //   callBackData.onSuccess(dataSnapshot.getValue(ProductRealTimeModel.class));
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
        myRefCoins.addValueEventListener(valueEventListener);
    }

    public void onStopRealTime() {
        if (valueEventListener != null)
            myRefCoins.removeEventListener(valueEventListener);
    }


}
