package com.example.enter_01.vfin.component.profiledetail;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.profile.ProfileManager;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.example.enter_01.vfin.utility.Utility;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailPresenter extends Presenter<ProfileDetailContract.View> implements  ProfileDetailContract.Presenter{

    private ProfileDetailContract.View view ;

    public ProfileDetailPresenter(ProfileDetailContract.View view){
        this.view = view;
    }

    @Override
    public void getProfile() {
        ProfileManager.getInstance().getMember(PreferencesMange.getInstance().getMemberID(),new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.setUpViewProfile((Member) t);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
