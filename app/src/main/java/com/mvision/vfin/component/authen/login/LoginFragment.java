package com.mvision.vfin.component.authen.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.facebook.FacebookAuthorizationException;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.register.RegisterActivity;
import com.mvision.vfin.component.authen.forgotpassword.requestforgotpassword.RequestForgotPasswordDialogFragment;
import com.mvision.vfin.component.main.MainActivity;
import com.mvision.vfin.customview.EditTextWithFont;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

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
    private int FACEBOOK = 1;
    private int VFIN = 2;


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @OnClick({R.id.buttonLogin, R.id.buttonLoginWithFacebook, R.id.textViewForgotPassword, R.id.textViewRegister})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonLogin:
                if (checkReadPhoneStatPermission()) {
                    clickVfin();
                } else {
                    setUpLocationPermission(VFIN);
                }
                break;
            case R.id.buttonLoginWithFacebook:
                if (checkReadPhoneStatPermission()) {
                    clickFaceBook();
                } else {
                    setUpLocationPermission(FACEBOOK);
                }
                break;
            case R.id.textViewForgotPassword:
                showForgotPassword();
                break;
            case R.id.textViewRegister:
                gotoRegister();
                break;
        }


    }

    private void showForgotPassword() {
        RequestForgotPasswordDialogFragment requestForgotPasswordDialogFragment = RequestForgotPasswordDialogFragment.newInstance();
        requestForgotPasswordDialogFragment.show(getFragmentManager(), "dialog");
    }

    private void gotoRegister() {
        startActivityFromFragment(RegisterActivity.class, null);
    }


    private void clickVfin() {
        presenter.getLogin(editTextTel.getTextDataNotNull(getActivity()
                .getString(R.string.error_username)), editTextPassword
                .getTextDataNotNull(getActivity().getString(R
                        .string.error_password)));
    }


    private void clickFaceBook() {
        setupFacebook();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList
                ("public_profile", "user_friends", "email"));
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
                if (error instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                }
                Log.e("FacebookException", error.toString());
                //   UtilsLog.error(TAG, "onError " + error);
//                new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText(getResources().getString(R.string.title_warning))
//                        .setContentText(getResources().getString(R.string.alert_network))
//                        .show();
            }
        });
    }

    private void setUpLocationPermission(int code) {
        if (shouldAskPermission()) {
            String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission
                    .ACCESS_FINE_LOCATION};

            try {
                requestPermissions(perms, code);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == VFIN) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                clickVfin();
            } else {
                Utility.ShowMsg(getActivity(), "กรุณาเปิด Permissions");
            }
        } else if (requestCode == FACEBOOK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                clickFaceBook();
            } else {
                Utility.ShowMsg(getActivity(), "กรุณาเปิด Permissions");
            }
        }


    }

    private boolean checkReadPhoneStatPermission() {
        String permission = Manifest.permission.READ_PHONE_STATE;
        String permissionLo = Manifest.permission.ACCESS_FINE_LOCATION;
        int res = getContext().checkCallingOrSelfPermission(permission);
        int resLo = getContext().checkCallingOrSelfPermission(permissionLo);
        return (res == PackageManager.PERMISSION_GRANTED && resLo == PackageManager.PERMISSION_GRANTED);
    }


    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);

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
        showProgress(getString(R.string.textLoading));
    }

    @Override
    public void hideLoading() {
       dismissProgress();
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
