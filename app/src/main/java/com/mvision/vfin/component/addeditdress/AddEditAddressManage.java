package com.mvision.vfin.component.addeditdress;

import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.request.Member;
import com.mvision.vfin.api.response.SaveAddressResponse;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/1/2017 AD.
 */

public class AddEditAddressManage {


    private static AddEditAddressManage instance = null;
    public static AddEditAddressManage getInstance() {
        if (instance == null)
            instance = new AddEditAddressManage();
        return instance;
    }


    public void saveAddress(AddressModel addressModel, final Query.CallBackData callBackData){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Member.class).saveAddress(PreferencesMange.getInstance().getMemberID(), addressModel)
                .subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SaveAddressResponse>() {
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
                    public void onNext(SaveAddressResponse s) {
                        Log.e("MyAddressResponseModel",new Gson().toJson(s));
                        callBackData.onSuccess(s);

                    }

                });
    }
}
