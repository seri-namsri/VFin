package com.example.enter_01.vfin.component.main;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.profile.model.Member;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface MainContract {

    interface View extends BaseView {

        void setUpView(Member member);

    }

    interface Presenter {
        void showView();

    }


}
