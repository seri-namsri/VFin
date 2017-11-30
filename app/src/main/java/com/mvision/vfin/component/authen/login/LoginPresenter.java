package com.mvision.vfin.component.authen.login;

import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;
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

        if (tel != null && password != null)
        {
            LoginManage.getInstance().loginWithApi(tel, password, callBackData);
        }
    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
            LoginResponseModel member = (LoginResponseModel) t;

            Utility.savePreferences(Contextor.getInstance().getContext(), "member_id",
                    member.result.member.memberCode);
            view.setUpViewLogin();
        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {


            view.showMessageFail(error);
        }
    };

    @Override
    public void getLoginFaceBook(String faceBookId) {
        LoginManage.getInstance().loginWithFaceBook(faceBookId, callBackData);
    }


}
