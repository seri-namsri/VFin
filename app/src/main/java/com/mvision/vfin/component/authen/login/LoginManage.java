package com.mvision.vfin.component.authen.login;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.mvision.vfin.api.modelrequest.LoginRequestModel;
import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.error.ErrorCode;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.firebase.Firestore.Update;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.RetrofitUtility;
import com.google.gson.Gson;
import java.util.ArrayList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginManage {

    private static LoginManage instance = null;

    public static LoginManage getInstance() {
        if (instance == null)
            instance = new LoginManage();
        return instance;
    }


    public void loginWithApi(final String userName, final String password, final Query.CallBackData callBackData) {
        Log.e("loginWithApi",new Gson().toJson(new LoginRequestModel(userName, password)));
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
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                loginWithApi(userName,password,callBackData);
                            }
                        });
                    }

                    @Override
                    public void onNext(LoginResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });

    }

    public void loginWithFaceBook(final String facebookID, final Query.CallBackData callBackData) {
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
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                callBackData.onFail(ErrorCode.getInstance().reload);
                                loginWithFaceBook(facebookID,callBackData);
                            }
                        });
                    }

                    @Override
                    public void onNext(LoginResponseModel s) {
                        callBackData.onSuccess(s);
                    }

                });

    }

}
