package com.mvision.vfin.component.myaddress;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.request.Member;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressManage {

    private static MyAddressManage instance = null;


    public static MyAddressManage getInstance() {
        if (instance == null)
            instance = new MyAddressManage();
        return instance;
    }

    public void myAddress(final Query.CallBackData callBackData) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Member.class).myAddress(PreferencesMange.getInstance().getMemberID()).subscribeOn
                (Schedulers
                .io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAddressResponseModel>() {
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
                    public void onNext(MyAddressResponseModel s) {
                        Log.e("MyAddressResponseModel",new Gson().toJson(s));
                        callBackData.onSuccess(s);

                    }

                });

    }

}