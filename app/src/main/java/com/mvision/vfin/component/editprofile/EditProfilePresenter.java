package com.mvision.vfin.component.editprofile;

import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.modelrequest.UpdateProfileRequestModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public class EditProfilePresenter extends Presenter<EditProfileContract.View> implements
        EditProfileContract.Presenter {

    private EditProfileContract.View view;
    private int layout;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        layout = extras.getInt("layoutEditProfile");
    }

    @Override
    public void getLayout() {
        if (layout == R.layout.layout_change_name)
            view.initVariableChangeName();
        else if (layout == R.layout.layout_change_email)
            view.initVariableChangeEmail();
        else if (layout == R.layout.layout_change_password)
            view.initVariableChangePassword();
        else if (layout == R.layout.layout_change_birtday)
            view.initVariableChangeBirthDay();
    }

    @Override
    public void upDateName(String name, String surname) {

        if (!name.isEmpty()&& !surname.isEmpty()){
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setFirstName(name);
            memberUpdate.setLastName(name);
            EditProfileManage.getInstance().updateProfile("name",memberUpdate,callBackData);
        }
    }

    @Override
    public void upDateEmail(String email) {
        if (!email.isEmpty()) {
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setEmail(email);
            EditProfileManage.getInstance().updateProfile("email", memberUpdate, callBackData);
        }
    }

    @Override
    public void upDatePassWord(String password,String newPassword,String passNewConfirm) {
        if (!password.isEmpty() && !newPassword.isEmpty() && !passNewConfirm.isEmpty()){
            if (newPassword.equals(passNewConfirm)){
                MemberUpdate memberUpdate = new MemberUpdate();
                memberUpdate.setPassword(password);
                memberUpdate.setNewPassword(newPassword);
                EditProfileManage.getInstance().updateProfile("password",memberUpdate,callBackData);
            }else {
                view.showMessageFail("รหัสผ่านไม่ตรงกัน");
            }
        }


    }

    @Override
    public void upDateBirthDay(String birthDay) {
        if (!birthDay.isEmpty()){
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setDateOfBirth(birthDay);
            EditProfileManage.getInstance().updateProfile("birthDay",memberUpdate,callBackData);
        }
    }

    public int getLayoutEdit() {
        return layout;
    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
              view.upDateSuccess((MemberUpdate) t);
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {
            view.showMessageFail(error);
        }
    };
}
