package com.mvision.vfin.component.authen.register;

import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.RegisterRequest;
import com.mvision.vfin.api.modelrequest.VerifyAccountRequest;
import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.api.response.RegisterResponseModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/22/2017 AD.
 */

public class RegisterManage {


    private static RegisterManage instance = null;

    public static RegisterManage getInstance() {
        if (instance == null)
            instance = new RegisterManage();
        return instance;
    }

    public void verifyAccount(final Query.CallBackData callBackData, final VerifyAccountRequest verifyAccountRequest){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request
                        .Member.class).verifyAccount(GetKey.getInstance()
                .apiVerifyAccount( GetKey
                        .getInstance()
                        .getSignatures()),verifyAccountRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ForgotPasswordModelRespone>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                verifyAccount(callBackData,verifyAccountRequest);
                            }
                            @Override
                            public void clickCancel() {
                                callBackData.onFail("");
                            }
                        });
                    }

                    @Override
                    public void onNext(ForgotPasswordModelRespone s) {
                        callBackData.onSuccess(s);
                    }
                });
    }

    public void register(final Query.CallBackData callBackData, final RegisterRequest registerRequest){
        Log.e("register",new Gson().toJson(registerRequest));
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request
                        .Member.class).register(GetKey.getInstance()
                .apiRegister(GetKey
                        .getInstance()
                        .getSignatures()),registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                register(callBackData,registerRequest);
                            }
                            @Override
                            public void clickCancel() {
                                callBackData.onFail("");
                            }
                        });
                    }

                    @Override
                    public void onNext(RegisterResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }


}
