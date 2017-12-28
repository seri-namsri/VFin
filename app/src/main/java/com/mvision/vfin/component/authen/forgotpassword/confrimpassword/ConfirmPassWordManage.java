package com.mvision.vfin.component.authen.forgotpassword.confrimpassword;

import com.mvision.vfin.api.modelrequest.VerifyOtpRequest;
import com.mvision.vfin.api.response.VerifyOtpResponseModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/21/2017 AD.
 */

public class ConfirmPassWordManage {

    private static ConfirmPassWordManage instance = null;

    public static ConfirmPassWordManage getInstance() {
        if (instance == null)
            instance = new ConfirmPassWordManage();
        return instance;
    }


    public void upDateNewPassword(final Query.CallBackData callBackData , final String mobilePhone,
                                  final String otpCode , final String refCode, final String password){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Member.class).updateNewPassword(GetKey.getInstance()
                .apiUpdatePassword(GetKey
                        .getInstance()
                        .getSignatures()),new
                VerifyOtpRequest(mobilePhone,otpCode,refCode,password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VerifyOtpResponseModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                upDateNewPassword(callBackData,mobilePhone,otpCode,refCode,password);
                            }

                            @Override
                            public void clickCancel() {
                                callBackData.onFail("");
                            }
                        });
                    }
                    @Override
                    public void onNext(VerifyOtpResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }

}
