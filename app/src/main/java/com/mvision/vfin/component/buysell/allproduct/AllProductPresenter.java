package com.mvision.vfin.component.buysell.allproduct;

import com.mvision.vfin.api.response.TimeResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.firebase.Firestore.Query;

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
                view.showNotFoundData();
                //    view.showMessageFail(error);
            }
        });

    }


    @Override
    public void stopRealTime() {
        AllProductManage.getInstance().onStopRealTime();
    }

    @Override
    public void buyProduct(final ProductRealTimeModel productModel) {

        AllProductManage.getInstance().tradeBuyProduct(new Query.CallBackDataTrade() {
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

            @Override
            public void onItemFail(int position) {
                view.changeItemFail(position);
            }
        },productModel);

    }


}
