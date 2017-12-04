package com.mvision.vfin.customview.dialoglist;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public interface DialogContract {
    interface View extends BaseView {
        void setUpViewProfile(String member);


    }

    interface Presenter {
        void getDataList();

    }
}
