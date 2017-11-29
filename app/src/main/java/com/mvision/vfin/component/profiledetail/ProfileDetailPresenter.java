package com.mvision.vfin.component.profiledetail;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import org.parceler.Parcels;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailPresenter extends Presenter<ProfileDetailContract.View> implements  ProfileDetailContract.Presenter{

    private ProfileDetailContract.View view ;

    public ProfileDetailPresenter(ProfileDetailContract.View view){
        this.view = view;
    }

    private MemberResponseModel memberResponseModel ;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        memberResponseModel = Parcels.unwrap(extras.getParcelable("profile"));
    }

    @Override
    public void getProfile() {
        view.setUpViewProfile(memberResponseModel);

    }
}
