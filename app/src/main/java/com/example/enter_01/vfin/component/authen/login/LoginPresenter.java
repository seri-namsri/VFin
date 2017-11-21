package com.example.enter_01.vfin.component.authen.login;

import com.example.enter_01.vfin.api.response.LoginResponseModel;
import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginPresenter extends Presenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.View view ;

    public LoginPresenter(LoginContract.View view){
        this.view = view ;
    }


    @Override
    public void getLogin(String tel,String password) {

        LoginMange.getInstance().loginWithApi(tel, password, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                Log.e("onSuccess",new Gson().toJson((LoginResponseModel)t));
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });


    /*  LoginMange.getInstance().getLogin(new Query.CallBackData() {
          @Override
          public <T> void onSuccess(T t) {

          }

          @Override
          public <T> void onSuccessAll(ArrayList<T> tArrayList) {
              Member member = (Member) tArrayList.get(0);
              Utility.savePreferences(Contextor.getInstance().getContext(),"member_id",
                      member.getMember_id());
              addDeviceToken(member);
              view.setUpViewLogin();
          }

          @Override
          public void onFail(String error) {

          }
      },tel,password);*/
    }


    public void addDeviceToken( Member member){
        ArrayList<String> arrayDeviceToken = member.getDevice_token();
        String deviceToken  = Utility.loadSavedPreferences(Contextor.getInstance().getContext(),
                "tokenFCM");

        if (arrayDeviceToken!= null && arrayDeviceToken.indexOf(deviceToken)<0){
            arrayDeviceToken.add(deviceToken);

        }else if (arrayDeviceToken == null){
            arrayDeviceToken = new ArrayList<>();
            arrayDeviceToken.add(deviceToken);
        }

        LoginMange.getInstance().addDeviceToken(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        },member.getMember_id(),arrayDeviceToken);

    }
}
