package com.mvision.vfin.component.profile;

import android.os.Bundle;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.component.profile.model.ProfileMore;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface ProfileContract {
    interface View extends BaseView {
        void setUpViewProfile(MemberResponseModel member);

        void setUpViewMore(ArrayList<ProfileMore> arrayList);


        void OpenActivity(Class className, Bundle bundle);

        void logOutSuccess();

        void startProfileDetail(Bundle bundle);

        void startLevelDetail();
    }

    interface Presenter {
        void getProfile();

        void clickItemMore(int position);

        void logOut();

        void clickProfile();

        void clickLevelDetail();



    }

}
