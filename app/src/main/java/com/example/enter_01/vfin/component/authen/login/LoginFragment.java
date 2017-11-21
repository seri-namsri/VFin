package com.example.enter_01.vfin.component.authen.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;
import com.example.enter_01.vfin.component.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.editTextTel)EditText editTextTel;
    @BindView(R.id.editTextPassword)EditText editTextPassword;
    private LoginPresenter presenter ;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @OnClick({R.id.buttonLogin})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonLogin :
                presenter.getLogin(editTextTel.getText().toString(),editTextPassword.getText
                        ().toString());
                break;
        }
    }


    @Override
    public void showMessageFail(String msg) {

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
          startActivityFromFragment(MainActivity.class,null);
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
