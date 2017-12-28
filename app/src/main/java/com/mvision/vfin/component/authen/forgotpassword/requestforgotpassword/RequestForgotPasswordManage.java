package com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword;

import com.mvision.vfin.api.modelrequest.ForgotPasswordRequest;
import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class RequestForgotPasswordManage {

    private static RequestForgotPasswordManage instance = null;

    public static RequestForgotPasswordManage getInstance() {
        if (instance == null)
            instance = new RequestForgotPasswordManage();
        return instance;
    }



    public void forgotPassword(final Query.CallBackData callBackData , final String mobilePhone,
                               final String otpType){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Member.class).forgotPassword(GetKey.getInstance().apiForgotPassword(GetKey
                .getInstance()
                .getSignatures()),new
                ForgotPasswordRequest(mobilePhone,otpType))
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
                                forgotPassword(callBackData,mobilePhone,otpType);
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
}
