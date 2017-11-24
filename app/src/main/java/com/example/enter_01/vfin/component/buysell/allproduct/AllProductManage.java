package com.example.enter_01.vfin.component.buysell.allproduct;

import com.example.enter_01.vfin.api.modelrequest.ErrorModel;
import com.example.enter_01.vfin.api.modelrequest.RequestFcm;
import com.example.enter_01.vfin.api.modelrequest.TradeBuy;
import com.example.enter_01.vfin.api.request.Apipublic;
import com.example.enter_01.vfin.api.response.ModelFcm;
import com.example.enter_01.vfin.api.response.TradeBuyResponseModel;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.firebase.realtime.FirebaseRealtimeManage;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.example.enter_01.vfin.utility.RetrofitUtility;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
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


    public void getAllProductFormRealtime(final Query.CallBackDataRealTime callBackData) {

        final long[] count = {0};
        final ArrayList<ProductRealTimeModel> productRealTimeModels = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("product");
        final ArrayList<String>arrayList = new ArrayList<>();

        final ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                ProductRealTimeModel productRealTimeModel = dataSnapshot.getValue
                        (ProductRealTimeModel.class);
                productRealTimeModels.add(productRealTimeModel);
                arrayList.add(productRealTimeModel.getId()+"");
                if (count[0] <= productRealTimeModels.size()) {
                    productRealTimeModel.setIntegerArrayListId(arrayList);
                    callBackData.onSuccessAll(productRealTimeModels);
                    //    productRealTimeModels.clear();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                int position = arrayList.indexOf(dataSnapshot.getKey());
                if (position >= 0) {
                    ProductRealTimeModel productRealTimeModel = dataSnapshot.getValue
                            (ProductRealTimeModel.class);
                    productRealTimeModels.set(position,productRealTimeModel);
                    callBackData.onSuccessItemChange(position);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
               int position  =  arrayList.indexOf(dataSnapshot.getKey());
                if (position >= 0){
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

                myRef.addChildEventListener(childEventListener);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void getProductIdFormRealtime(FirebaseDatabase database, String id, final Query
            .CallBackData
            callBackData) {


        DatabaseReference myRef = database.getReference("product/" + id);
        final String[] key = {""};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callBackData.onSuccess(dataSnapshot.getValue(ProductRealTimeModel.class));
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("getAllProductFormRealtime", "Failed to read value.", error.toException());
            }
        });


    }


    public void tradeBuyProduct(final Query.CallBackData callBackData, ProductRealTimeModel productRealTimeModel) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.example.enter_01.vfin.api.request
                        .Product.class).tradebuy(new TradeBuy(PreferencesMange.getInstance().getMemberID(),
                productRealTimeModel
                        .getNextPrice(),
                productRealTimeModel.getId()))
                .subscribeOn(Schedulers
                        .io())
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
                                ErrorModel errorModel = new Gson().fromJson(body
                                        .string(), ErrorModel.class);
                                callBackData.onFail(errorModel.message);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onNext(TradeBuyResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }


}
