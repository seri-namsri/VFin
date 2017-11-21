package com.example.enter_01.vfin.component.viewads;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.viewads.pojo.ViewAdsModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public interface ViewAdsContract {

    interface View extends BaseView {
        void setUpViewAds(ArrayList<ViewAdsModel>ads);
    }

    interface Presenter {
        void getViewAds();

    }

}
