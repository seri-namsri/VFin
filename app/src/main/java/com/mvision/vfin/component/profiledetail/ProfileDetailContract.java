package com.mvision.vfin.component.profiledetail;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProfileDetailContract {

    interface View extends BaseView {

        void setUpViewProfile(MemberResponseModel member);
    }

    interface Presenter {
        void getProfile();

    }
}
