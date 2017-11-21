package com.example.enter_01.vfin.component.financehistory;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.financehistory.pojo.FinanceHistoryModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryPresenter extends Presenter<FinanceHistoryContract
        .View> implements FinanceHistoryContract.Presenter{

    private FinanceHistoryContract.View view;

    public FinanceHistoryPresenter(FinanceHistoryContract
                                           .View view){
        this.view = view ;
    }

    @Override
    public void getFinanceHistory() {
         FinanceHistoryManage.getInstance().getFinanceHistory(new Query.CallBackData() {
             @Override
             public <T> void onSuccess(T t) {

             }

             @Override
             public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                   view.setUpViewFinanceHistory((ArrayList<FinanceHistoryModel>) tArrayList);
             }

             @Override
             public void onFail(String error) {

             }
         });
    }
}
