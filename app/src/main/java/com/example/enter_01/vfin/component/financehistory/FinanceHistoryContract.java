package com.example.enter_01.vfin.component.financehistory;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public interface FinanceHistoryContract {

    interface View extends BaseView {

        void setUpViewFinanceHistory(ArrayList<FinanceHistoryModel>viewFinanceHistory);

    }

    interface Presenter {
        void getFinanceHistory();

    }
}
