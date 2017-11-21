package com.example.enter_01.vfin.component.authen.login;

import com.example.enter_01.vfin.api.modelrequest.LoginRequestModel;
import com.example.enter_01.vfin.api.response.LoginResponseModel;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.firebase.Firestore.Update;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.RetrofitUtility;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginMange {

    private static FirebaseFirestore db;
    private static LoginMange instance = null;

    public static LoginMange getInstance() {
        if (instance == null)
            instance = new LoginMange();
        return instance;
    }

    public void getLogin(final Query.CallBackData callBackData, String tel, String password) {
        db = FirebaseFirestore.getInstance();

        Query.getInstance().readDataCollection(db.collection("member").whereEqualTo("tel", tel)
                .whereEqualTo("password", password), new
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

    public void addDeviceToken(final Query.CallBackData callBackData, String memberId,
                               ArrayList<String> deviceToken) {
        db = FirebaseFirestore.getInstance();

        Update.getInstance().updateDocument(db.document("member/" + memberId).update
                        ("device_token", deviceToken)
                , new Update
                        .CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {

                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });


    }



    public void loginWithApi(String userName, String password, final Query.CallBackData callBackData){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.example.enter_01.vfin.api.request
                .Member.class).loginVfin(new LoginRequestModel(userName,password)).subscribeOn(Schedulers
                .io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        e.printStackTrace();
                        //    view.hideLoading();
                    }

                    @Override
                    public void onNext(LoginResponseModel s) {
                        callBackData.onSuccess(s);
                    }

                });

    }

}
