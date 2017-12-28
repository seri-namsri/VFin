package com.mvision.vfin.component.financehistory;

import com.mvision.vfin.api.response.WalletTransectionResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public interface FinanceHistoryContract {

    interface View extends BaseView {

        void setUpViewFinanceHistory(WalletTransectionResponseModel walletTransectionResponseModel);
        void setUpViewFinanceHistoryMore(WalletTransectionResponseModel  walletTransectionResponseModel);
        void setUpCoin(String coin);
        void setUpViewFinanceHistoryLoading();
        void showTextNotFound();

    }

    interface Presenter {
        void getFinanceHistory();
        void getFinanceHistoryMore();
        void getCoin();
        void stopRealTime();

    }
}
