package com.example.enter_01.vfin.component.main;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.profile.ProfileManager;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.PreferencesMange;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.View view ;

    public MainPresenter (MainContract.View view ){
        this.view = view;
    }

    @Override
    public void showView() {

        ProfileManager.getInstance().getMember(PreferencesMange.getInstance().getMemberID(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.setUpView((Member) t);
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
