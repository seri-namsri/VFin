package com.mvision.vfin.component.topup;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/24/2017 AD.
 */

public interface TopUpContract {

    interface View extends BaseView {
        void showTopup();
    }

    interface Presenter {
        void getTupUp();

    }


}
