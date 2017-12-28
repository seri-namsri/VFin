package com.mvision.vfin.component.financehistory;

import com.mvision.vfin.api.response.WalletTransectionResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryPresenter extends Presenter<FinanceHistoryContract
        .View> implements FinanceHistoryContract.Presenter {

    private FinanceHistoryContract.View view;
    private int pageSize = 10;
    private int startPosition = 0;

    public FinanceHistoryPresenter(FinanceHistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void getFinanceHistory() {
        view.showLoading();
        FinanceHistoryManage.getInstance().getFinanceHistory(callBackData, pageSize, startPosition);
    }

    @Override
    public void getFinanceHistoryMore() {
        FinanceHistoryManage.getInstance().getFinanceHistory(callBackData, pageSize,
                startPosition * pageSize);
    }

    @Override
    public void getCoin() {
        FinanceHistoryManage.getInstance().getCoin(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                ModelCoinAndBit modelCoinAndBit = (ModelCoinAndBit) t;
                view.setUpCoin(modelCoinAndBit.getWallet() + "");
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void stopRealTime() {
        FinanceHistoryManage.getInstance().stopFileBaseRealtime();
    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {

            WalletTransectionResponseModel walletTransectionResponseModel =
                    (WalletTransectionResponseModel) t;
            if (walletTransectionResponseModel.result != null && walletTransectionResponseModel.result.size() > 0) {
                if (walletTransectionResponseModel.result.size() >= pageSize)
                    walletTransectionResponseModel.result.add(null);

                if (startPosition == 0) {
                    view.setUpViewFinanceHistory(walletTransectionResponseModel);
                    view.hideLoading();
                } else {
                    view.setUpViewFinanceHistoryMore(walletTransectionResponseModel);
                }

                startPosition++;
            } else if (walletTransectionResponseModel.result != null &&
                    walletTransectionResponseModel.result.size() == 0) {
                view.setUpViewFinanceHistoryLoading();

                if (startPosition == 0) {
                    view.showTextNotFound();
                }
            }
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {

        }
    };
}
