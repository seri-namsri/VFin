package com.mvision.vfin.component.profiledetail;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.profile.model.Address;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import java.io.File;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProfileDetailContract {

    interface View extends BaseView {
        void setUpViewProfile(MemberResponseModel member);
        void setUpViewAddress(AddressModel addressModel);
        void startEditProfile(Bundle bundle,int requestCode);
        void startAddAddress(int requestCode);
        void startChangeAddress(int requestCode);
        void startEditAddress(Bundle bundle,int requestCode);
        void finishFragment(MemberResponseModel member);
        void onRequestPermissions(String[] perms,int permsRequestCode);
        void showGallery();
    }

    interface Presenter {
        void getProfile();
        void clickEditProfile(int id);
        void changeData(int id, Intent data);
        void getfinishFragment();
        void uploadImageProfile(File file);
        void checkPermissions(int requestCode, String[] permissions, int[] grantResults);


    }
}
