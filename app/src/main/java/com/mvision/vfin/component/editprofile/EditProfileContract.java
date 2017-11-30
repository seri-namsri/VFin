package com.mvision.vfin.component.editprofile;

import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public interface EditProfileContract {

    interface View extends BaseView {
        void setUpView(int layout);
        void initVariableChangeName();
        void initVariableChangePassword();
        void initVariableChangeEmail();
        void initVariableChangeBirthDay();
        void upDateSuccess(MemberUpdate memberUpdate);
    }

    interface Presenter {

        void getLayout();
        void upDateName(String name,String surname);
        void upDateEmail(String email);
        void upDatePassWord(String password,String newPassword,String passNewConfirm);
        void upDateBirthDay(String birthDay);
    }

}
