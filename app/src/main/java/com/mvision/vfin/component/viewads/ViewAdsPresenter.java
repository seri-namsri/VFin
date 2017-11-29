package com.mvision.vfin.component.viewads;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.viewads.pojo.ViewAdsModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsPresenter extends Presenter<ViewAdsContract.View> implements ViewAdsContract.Presenter {

    private ViewAdsContract.View view ;

    public ViewAdsPresenter(ViewAdsContract.View view){
        this.view = view;
    }

    @Override
    public void getViewAds() {
         ViewAdsManage.getInstance().getAds(new Query.CallBackData() {
             @Override
             public <T> void onSuccess(T t) {

             }

             @Override
             public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                 view.setUpViewAds((ArrayList<ViewAdsModel>) tArrayList);
             }

             @Override
             public void onFail(String error) {

             }
         });
    }
}
