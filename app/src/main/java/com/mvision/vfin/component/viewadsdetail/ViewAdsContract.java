package com.mvision.vfin.component.viewadsdetail;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public interface ViewAdsContract {

    interface View extends BaseView {
        void setUpViewAds();
    }

    interface Presenter {
        void getViewAds();

    }
}
