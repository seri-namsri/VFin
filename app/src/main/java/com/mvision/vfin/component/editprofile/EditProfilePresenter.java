package com.mvision.vfin.component.editprofile;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.modelrequest.UpdateProfileRequestModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.myaddress.Model.Province;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public class EditProfilePresenter extends Presenter<EditProfileContract.View> implements
        EditProfileContract.Presenter {

    private EditProfileContract.View view;
    private int layout;
    private MemberResponseModel memberResponseModel;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        memberResponseModel = Parcels.unwrap(extras.getParcelable("profile"));
        layout = extras.getInt("layoutEditProfile");
    }


    @Override
    public void getLayout() {
        if (layout == R.layout.layout_change_name)
            view.initVariableChangeName(memberResponseModel);
        else if (layout == R.layout.layout_change_email)
            view.initVariableChangeEmail(memberResponseModel);
        else if (layout == R.layout.layout_change_password)
            view.initVariableChangePassword();
        else if (layout == R.layout.layout_change_birtday)
            view.initVariableChangeBirthDay(memberResponseModel);
        else if (layout == R.layout.layout_change_gender)
            view.initVariableChangeGender(memberResponseModel);
        else if (layout == R.layout.layout_change_iden)
            view.initVariableChangePersonal();
    }

    @Override
    public void upDateName(String name, String surname) {

        if (!name.isEmpty()&& !surname.isEmpty()){
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setFirstName(name);
            memberUpdate.setLastName(surname);
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

    @Override
    public void upDateGender(String gender) {
        if (!gender.isEmpty()){
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setGender(gender);
            EditProfileManage.getInstance().updateProfile("gender",memberUpdate,callBackData);
        }
    }

    @Override
    public void upDatePersonal(String personalId) {
        if (!personalId.isEmpty()){
            MemberUpdate memberUpdate = new MemberUpdate();
            memberUpdate.setPersonalId(personalId);
            EditProfileManage.getInstance().updateProfile("personalId",memberUpdate,callBackData);
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
