package com.mvision.vfin.component.downloadapp;

import com.mvision.vfin.component.downloadapp.pojo.DownloadAppModel;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/9/2017 AD.
 */

public class DownloadAppManage {


    private static DownloadAppManage instance = null;
    public static DownloadAppManage getInstance() {
        if (instance == null)
            instance = new DownloadAppManage();
        return instance;
    }

    public void getApplication(final Query.CallBackData callBackData) {

    }
}
