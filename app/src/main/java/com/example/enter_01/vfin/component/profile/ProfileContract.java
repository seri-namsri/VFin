package com.example.enter_01.vfin.component.profile;

import android.os.Bundle;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.component.profile.model.ProfileMore;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface ProfileContract {
    interface View extends BaseView {
        void setUpViewProfile(Member member);

        void setUpViewMore(ArrayList<ProfileMore> arrayList);


        void OpenActivity(Class className, Bundle bundle);

        void logOutSuccess();
    }

    interface Presenter {
        void getProfile();

        void clickItemMore(int position);

        void logOut();

    }

}
