package com.mvision.vfin.component.provinceamphur;

import android.os.Bundle;

import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public class ProvinceAmphurPresenter extends Presenter<ProvinceAmphurContract.View> implements
        ProvinceAmphurContract.Presenter {

    private int keyCode  ;
    private int provinceId ;
    private ProvinceAmphurContract.View view ;
    public ProvinceAmphurPresenter(ProvinceAmphurContract.View view){
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        keyCode = extras.getInt("keyCode");
        provinceId = extras.getInt("provinceId");
    }

    @Override
    public void getProvinceOrAmphur() {
        if (keyCode == 1){
            ProvinceAmphurManage.getInstance().getProvince(callBackData);
        }else if (keyCode == 2 && provinceId >= 0){
            ProvinceAmphurManage.getInstance().getAmmphur(callBackData,provinceId);
        }

    }


    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
            if (keyCode == 1){
                view.setProvince((ProvinceResponseModel) t);
            }else if (keyCode == 2){
                view.setAmphur((AmphurResponseModel) t);
            }
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {

        }
    };

    @Override
    public void clickProvince(Province province) {
        view.sentProvince(province);
    }

    @Override
    public void clickAmphur(Amphur amphur) {
        view.sentAmphur(amphur);
    }
}
