package com.example.enter_01.vfin.component.leveldetail;

import com.example.enter_01.vfin.base.BaseView;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public interface LevelDetailContract {
    interface View extends BaseView {

        void setUpView();

    }

    interface Presenter {
        void showView();

    }
}
