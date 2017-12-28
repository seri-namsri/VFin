package com.mvision.vfin.component.authen.login;

import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.Utility;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginPresenter extends Presenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }


    @Override
    public void getLogin(String tel, String password) {
        if (tel != null && password != null) {
            view.showLoading();
            LoginManage.getInstance().loginWithApi(tel, password, callBackData);
        }
    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
            view.hideLoading();
            LoginResponseModel member = (LoginResponseModel) t;
            PreferencesMange.getInstance().setMemberID(member.result.memberCode);
            PreferencesMange.getInstance().setTokenSession(member.result.tokenSession);
            view.setUpViewLogin();
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {
            Log.e("DDDDDDDDD","DDDDDDD");
            view.hideLoading();
            view.showMessageFail(error);
        }
    };

    @Override
    public void getLoginFaceBook(String faceBookId) {
        view.showLoading();
        LoginManage.getInstance().loginWithFaceBook(faceBookId, callBackData);
    }


}
