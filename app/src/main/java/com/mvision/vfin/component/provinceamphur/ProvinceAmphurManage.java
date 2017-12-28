package com.mvision.vfin.component.provinceamphur;

import com.mvision.vfin.api.request.Member;
import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/6/2017 AD.
 */

public class ProvinceAmphurManage {


    private static ProvinceAmphurManage instance = null;
    public static ProvinceAmphurManage getInstance() {
        if (instance == null)
            instance = new ProvinceAmphurManage();
        return instance;
    }


    public void getProvince( final Query.CallBackData callBackData){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Member.class).getProvince()
                .subscribeOn
                        (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinceResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getProvince(callBackData);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(ProvinceResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });
    }


    public void getAmmphur(final Query.CallBackData callBackData, final int provinceId){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(Member.class).getAmphur(provinceId)
                .subscribeOn
                        (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AmphurResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getAmmphur(callBackData,provinceId);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(AmphurResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });
    }
}
