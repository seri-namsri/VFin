package com.example.enter_01.vfin.component.downloadapp;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.downloadapp.pojo.DownloadAppModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class DownloadAppPresenter extends Presenter<DownloadAppContract.View> implements
        DownloadAppContract.Presenter{

    private DownloadAppContract.View view ;

    public DownloadAppPresenter(DownloadAppContract.View view){
        this.view = view;
    }

    @Override
    public void getDownloadApp() {
        DownloadAppManage.getInstance().getApplication(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                view.setUpViewDownloadApp((ArrayList<DownloadAppModel>) tArrayList);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
