package com.mvision.vfin.component.addeditdress;

import com.google.gson.Gson;
import com.mvision.vfin.api.request.Member;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.api.response.SaveAddressResponse;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;
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


    public void saveAddress(final AddressModel addressModel, final Query.CallBackData callBackData){
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
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                saveAddress(addressModel,callBackData);
                            }
                        });
                    }

                    @Override
                    public void onNext(SaveAddressResponse s) {
                        callBackData.onSuccess(s.result);

                    }

                });
    }

}
