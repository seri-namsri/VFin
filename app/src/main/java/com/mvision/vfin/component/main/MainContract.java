package com.mvision.vfin.component.main;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface MainContract {

    interface View extends BaseView {

        void setUpView(ModelCoinAndBit modelCoinAndBit);

    }

    interface Presenter {
        void showView();

    }


}
