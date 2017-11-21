package com.example.enter_01.vfin.component.profiledetail;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.profile.model.Member;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProfileDetailContract {

    interface View extends BaseView {

        void setUpViewProfile(Member member);
    }

    interface Presenter {
        void getProfile();

    }
}
