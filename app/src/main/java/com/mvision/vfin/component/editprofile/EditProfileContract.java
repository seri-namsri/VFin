package com.mvision.vfin.component.editprofile;

import android.content.Intent;

import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public interface EditProfileContract {

    interface View extends BaseView {
        void setUpView(int layout);
        void initVariableChangeName(MemberResponseModel memberResponseModel);
        void initVariableChangePassword();
        void initVariableChangeEmail(MemberResponseModel memberResponseModel);
        void initVariableChangeBirthDay(MemberResponseModel memberResponseModel);
        void initVariableChangeGender(MemberResponseModel memberResponseModel);
        void initVariableChangePersonal();
        void upDateSuccess(MemberUpdate memberUpdate);
    }

    interface Presenter {

        void getLayout();
        void upDateName(String name,String surname);
        void upDateEmail(String email);
        void upDatePassWord(String password,String newPassword,String passNewConfirm);
        void upDateBirthDay(String birthDay);
        void upDateGender(String gender);
        void upDatePersonal(String personalId);

    }

}
