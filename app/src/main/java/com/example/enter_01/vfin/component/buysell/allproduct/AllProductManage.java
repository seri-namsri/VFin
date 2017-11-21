package com.example.enter_01.vfin.component.buysell.allproduct;

import com.example.enter_01.vfin.api.modelrequest.RequestFcm;
import com.example.enter_01.vfin.api.request.Apipublic;
import com.example.enter_01.vfin.api.response.ModelFcm;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.RetrofitUtility;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class AllProductManage {

    private static AllProductManage instance = null;
    private static FirebaseFirestore db;

    public static AllProductManage getInstance() {
        if (instance == null)
            instance = new AllProductManage();
        return instance;
    }

    public void getAllProduct(final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataCollection(db.collection("product"), new
                ProductModel(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

                callBackData.onSuccess(t);


            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                callBackData.onSuccessAll(tArrayList);
            }

            @Override
            public void onFail(String error) {
                callBackData.onFail(error);
            }
        });
    }


    public void getMemberOfProduct(final Query.CallBackData callBackData,String memberId) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocumentNorealTime(db.document("member/"+memberId), new
                Member(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

                callBackData.onSuccess(t);


            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                callBackData.onSuccessAll(tArrayList);
            }

            @Override
            public void onFail(String error) {
                callBackData.onFail(error);
            }
        });
    }

    public void getProduct(final Query.CallBackData callBackData,String productId) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocument(db.document("product/"+productId), new
                ProductModel(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

                callBackData.onSuccess(t);


            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                callBackData.onSuccessAll(tArrayList);
            }

            @Override
            public void onFail(String error) {
                callBackData.onFail(error);
            }
        });
    }


    public void setNotiFcm(ArrayList<String>deviceToken,String productName,String imageProduct  ,
                           int price){
        String priority = "high";
        String title =  productName+" ถูกซื้อต่อไปแล้ว";
        String body = "ด้วยราคา "+price + " บาท";
        String icon = "icon_noti";
        String color = "#0064FF";
        RetrofitUtility.getInstance().getRetrofit("https://fcm.googleapis.com").
                create(Apipublic.class).sendFcm(new RequestFcm(deviceToken, priority, title, body,
                icon, color,imageProduct))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ModelFcm>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(final ModelFcm s) {

                    }
                });
    }



}
