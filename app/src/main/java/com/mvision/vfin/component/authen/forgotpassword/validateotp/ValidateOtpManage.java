package com.mvision.vfin.component.authen.forgotpassword.validateotp;

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
 * Created by enter_01 on 11/6/2017 AD.
 */

public class ValidateOtpManage {

    private static ValidateOtpManage instance = null;

    public static ValidateOtpManage getInstance() {
        if (instance == null)
            instance = new ValidateOtpManage();
        return instance;
    }



    public void validateOtp(final Query.CallBackData callBackData , final String mobilePhone,
                            final String otpCode , final String refCode){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Member.class).verifyOtp(GetKey.getInstance()
                .apiVerifyOtp(GetKey
                .getInstance()
                .getSignatures()),new
                VerifyOtpRequest(mobilePhone,otpCode,refCode,null))
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
                                validateOtp(callBackData,mobilePhone,otpCode,refCode);
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
