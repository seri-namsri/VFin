package com.mvision.vfin.component.viewads;



/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsManage {

    private static ViewAdsManage instance = null;
    public static ViewAdsManage getInstance() {
        if (instance == null)
            instance = new ViewAdsManage();
        return instance;
    }

}
