package com.example.enter_01.vfin.component.downloadapp;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.downloadapp.pojo.DownloadAppModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public interface DownloadAppContract {

    interface View extends BaseView {
        void setUpViewDownloadApp(ArrayList<DownloadAppModel> downloadAppModels);

    }

    interface Presenter {

        void getDownloadApp();

    }
}
