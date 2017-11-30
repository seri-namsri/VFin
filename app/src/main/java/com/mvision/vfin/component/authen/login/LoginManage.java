package com.mvision.vfin.component.authen.login;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.mvision.vfin.api.modelrequest.LoginRequestModel;
import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.firebase.Firestore.Update;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.RetrofitUtility;
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
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginManage {

    private static FirebaseFirestore db;
    private static LoginManage instance = null;

    public static LoginManage getInstance() {
        if (instance == null)
            instance = new LoginManage();
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


    public void loginWithApi(String userName, String password, final Query.CallBackData callBackData) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request
                        .Member.class).loginVfin(new LoginRequestModel(userName, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
                    public void onNext(LoginResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }

    public void loginWithFaceBook(String facebookID, final Query.CallBackData callBackData) {
        LoginFaceBookRequestModel a = new LoginFaceBookRequestModel(facebookID);
        Log.e("loginWithFaceBook",new Gson().toJson(a));
        RetrofitUtility.getInstance().getRetrofit().create(com.mvision.vfin.api.request.Member.class)
                .loginFaceBookVfin(new LoginFaceBookRequestModel(facebookID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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


                        //    view.hideLoading();
                    }

                    @Override
                    public void onNext(LoginResponseModel s) {
                        callBackData.onSuccess(s);
                    }

                });

    }

}
