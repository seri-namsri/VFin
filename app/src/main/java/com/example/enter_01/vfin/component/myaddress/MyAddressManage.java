package com.example.enter_01.vfin.component.myaddress;

import com.example.enter_01.vfin.api.modelrequest.ErrorModel;
import com.example.enter_01.vfin.api.modelrequest.MyAddressRequestModel;
import com.example.enter_01.vfin.api.request.Member;
import com.example.enter_01.vfin.api.response.MyAddressResponseModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.RetrofitUtility;
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
                .create(Member.class).myAddress("ce7581e2-a12e-40af-bb55-57c60f918e74").subscribeOn
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
