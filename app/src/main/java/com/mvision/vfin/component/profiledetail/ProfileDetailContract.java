package com.mvision.vfin.component.profiledetail;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import java.io.File;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProfileDetailContract {

    interface View extends BaseView {

        void setUpViewProfile(MemberResponseModel member);
        void startEditProfile(Bundle bundle,int requestCode);
        void finishFragment(MemberResponseModel member);
        void showGallery();
    }

    interface Presenter {
        void getProfile();
        void clickEditProfile(int id);
        void changeData(int id, Intent data);
        void getfinishFragment();
        void uploadImageProfile(File file);


    }
}
