package com.mvision.vfin.component.profiledetail;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailPresenter extends Presenter<ProfileDetailContract.View> implements ProfileDetailContract.Presenter {

    private ProfileDetailContract.View view;
    private int requestCodeName = 1;
    private int requestCodeEmail = 2;
    private int requestCodeBirthDay = 3;
    private int requestCodeUploadProfile = 7;

    public ProfileDetailPresenter(ProfileDetailContract.View view) {
        this.view = view;
    }

    private MemberResponseModel memberResponseModel;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        memberResponseModel = Parcels.unwrap(extras.getParcelable("profile"));
    }

    @Override
    public void getProfile() {
        view.setUpViewProfile(memberResponseModel);

    }

    @Override
    public void clickEditProfile(int id) {
        int requestCode = 0;
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.linearLayoutName:
                requestCode = requestCodeName;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_name);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutEmail:
                requestCode = requestCodeEmail;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_email);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutBirdDay:
                requestCode = requestCodeBirthDay;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_birtday);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutPassword:
                requestCode = 0;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_password);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.imageViewProfile:
                view.showGallery();
                break;


        }

    }

    @Override
    public void changeData(int id, Intent data) {
        try {
            MemberUpdate memberUpdate = Parcels.unwrap(data.getExtras().getParcelable("memberUpdate"));
            if (id == requestCodeName) {

                memberResponseModel.result.firstName = memberUpdate.getFirstName();
                memberResponseModel.result.lastName = memberUpdate.getLastName();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeEmail) {
                memberResponseModel.result.email = memberUpdate.getEmail();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeBirthDay) {
                memberResponseModel.result.dateOfBirth = memberUpdate.getDateOfBirth();
                view.setUpViewProfile(memberResponseModel);
            }

        } catch (Exception e) {
            if (id == requestCodeUploadProfile) {
                try {
                    InputStream is = Contextor.getInstance().getContext().getContentResolver().openInputStream(data.getData());
                    upDateImage(getBytes(is));

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private void upDateImage(byte[] imageBytes) {
        ProfileDetailManage.getInstance().upLoadImage(imageBytes, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                UpdateProfileResponseModel updateProfileResponseModel = (UpdateProfileResponseModel) t;
                memberResponseModel.result.avatarLink = updateProfileResponseModel.result
                        .avatarLink;
                view.setUpViewProfile(memberResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.showMessageFail(error);
            }
        });
    }


    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    @Override
    public void getfinishFragment() {
        view.finishFragment(memberResponseModel);
    }

    @Override
    public void uploadImageProfile(File file) {

    }
}
