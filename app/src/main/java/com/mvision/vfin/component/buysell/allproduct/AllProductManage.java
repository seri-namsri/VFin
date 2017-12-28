package com.mvision.vfin.component.buysell.allproduct;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.TradeBuy;
import com.mvision.vfin.api.request.Apipublic;
import com.mvision.vfin.api.response.TimeResponseModel;
import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class AllProductManage {

    private static AllProductManage instance = null;

    public static AllProductManage getInstance() {
        if (instance == null)
            instance = new AllProductManage();
        return instance;
    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<String> arrayList;
    private ChildEventListener childEventListener;
    private DatabaseReference myRef;
    private DatabaseReference myRefCoins;
    private ArrayList<ProductRealTimeModel> productRealTimeModels ;
    public void getAllProductFormRealtime(final Query.CallBackDataRealTime callBackData) {
        final long[] count = {0};
        productRealTimeModels = new ArrayList<>();
        myRef = database.getReference("product");
        arrayList = new ArrayList<>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                ProductRealTimeModel productRealTimeModel = dataSnapshot.getValue
                        (ProductRealTimeModel.class);
                productRealTimeModels.add(productRealTimeModel);
                arrayList.add(productRealTimeModel.getCode() + "");
                if (count[0] <= productRealTimeModels.size()) {
                    productRealTimeModel.setIntegerArrayListId(arrayList);
                    callBackData.onSuccessAll(productRealTimeModels);
                    //    productRealTimeModels.clear();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                try {
                    int position = arrayList.indexOf(dataSnapshot.getKey());
                    if (position >= 0) {
                        ProductRealTimeModel productRealTimeModel = dataSnapshot.getValue(ProductRealTimeModel.class);
                        productRealTimeModels.set(position, productRealTimeModel);
                        callBackData.onSuccessItemChange(position);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int position = arrayList.indexOf(dataSnapshot.getKey());
                if (position >= 0) {
                    arrayList.remove(position);
                    productRealTimeModels.remove(position);
                    callBackData.onSuccessRemove(position);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                count[0] = dataSnapshot.getChildrenCount();
                if (count[0] == 0) {
                    callBackData.onFail("");
                }
                myRef.addChildEventListener(childEventListener);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

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
                // Failed to read value
                //   Log.w("getAllProductFormRealtime", "Failed to read value.", error.toException());
            }
        };
        myRefCoins.addValueEventListener(valueEventListener);
    }


    public void tradeBuyProduct(final Query.CallBackDataTrade callBackData, final ProductRealTimeModel productRealTimeModel) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Product.class)
                .tradeBuy(GetKey.getInstance()
                        .apiTradeBid(GetKey
                                .getInstance()
                                .getSignatures()),new TradeBuy(PreferencesMange.getInstance()
                        .getMemberID(), productRealTimeModel.getNextPrice(),
                        productRealTimeModel.getCode() + ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TradeBuyResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();

                            try {
                                ErrorModel errorModel = new Gson().fromJson(body.string(), ErrorModel.class);
                                callBackData.onFail(errorModel.message);

                                int position = arrayList.indexOf(productRealTimeModel.getCode() + "");
                                if (position >= 0) {
                                    callBackData.onItemFail(position);
                                }

                            } catch (IOException e1) {
                                e1.printStackTrace();
                                callBackData.onFail(e1.getMessage());

                            }
                        }

                    }

                    @Override
                    public void onNext(TradeBuyResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }

    public void getTime(final Query.CallBackData callBackData) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Apipublic.class)
                .getTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TimeResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {

                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(TimeResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });
    }

    public void onStopRealTime() {
        if (childEventListener != null)
            myRef.removeEventListener(childEventListener);

        if (valueEventListener != null)
            myRefCoins.removeEventListener(valueEventListener);
    }


}
