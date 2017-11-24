package com.example.enter_01.vfin.component.buysell.allproduct;

import android.os.CountDownTimer;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.example.enter_01.vfin.component.productdetail.ProductDetailManage;
import com.example.enter_01.vfin.component.profile.ProfileManager;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class AllProductPresenter extends Presenter<AllProductContract.View> implements AllProductContract.Presenter {

    private AllProductContract.View view;

    public AllProductPresenter(AllProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getProductAll() {
        view.showLoading();
        AllProductManage.getInstance().getAllProductFormRealtime(new Query.CallBackDataRealTime() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessRemove(int position) {
                view.removeItem(position);
            }

            @Override
            public <T> void onSuccessItemChange(int position) {
                 view.changeItem(position);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                view.hideLoading();
                ArrayList<ProductRealTimeModel> arrayList = (ArrayList<ProductRealTimeModel>) tArrayList;
                view.setUpViewAllProduct(arrayList);
            }

            @Override
            public void onFail(String error) {
                view.hideLoading();
                view.showMessageFail(error);
            }
        });

    }

    @Override
    public void buyProduct(final ProductRealTimeModel productModel) {

        AllProductManage.getInstance().tradeBuyProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.showMessageFail(error);
            }
        },productModel);

    }


}
