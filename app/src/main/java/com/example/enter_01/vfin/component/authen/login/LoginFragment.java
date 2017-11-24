package com.example.enter_01.vfin.component.authen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;
import com.example.enter_01.vfin.component.main.MainActivity;
import com.example.enter_01.vfin.customview.EditTextWithFont;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.editTextTel)
    EditTextWithFont editTextTel;
    @BindView(R.id.editTextPassword)
    EditTextWithFont editTextPassword;
    private LoginPresenter presenter;
    private CallbackManager callbackManager;
    ;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @OnClick({R.id.buttonLogin, R.id.buttonLoginWithFacebook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                presenter.getLogin(editTextTel.getTextDataNotNull(getActivity()
                          .getString(R.string.error_username)), editTextPassword
                          .getTextDataNotNull(getActivity().getString(R
                          .string.error_password)));
                break;
            case R.id.buttonLoginWithFacebook:
                setupFacebook();
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList
                        ("public_profile", "user_friends", "email"));
                break;
        }
    }

    private void setupFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                String facebookId = jsonObject.optString("id");
                                presenter.getLoginFaceBook(facebookId);
                            }
                        }
                );
                Bundle params = new Bundle();
                params.putString("fields", "id,birthday,first_name,gender,last_name,email");
                request.setParameters(params);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                //   UtilsLog.error(TAG, "onError " + error);
//                new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText(getResources().getString(R.string.title_warning))
//                        .setContentText(getResources().getString(R.string.alert_network))
//                        .show();
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showMessageFail(String msg) {
        Utility.ShowMsg(getActivity(), msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setUpViewLogin() {
        getActivity().finish();
        startActivityFromFragment(MainActivity.class, null);
    }

    @Override
    protected void initializePresenter() {
        presenter = new LoginPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_login;
    }
}
