package com.mvision.vfin.component.forgotpassword.confrimpassword;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ConfirmPasswordFragment extends BaseFragment implements ConfirmPasswordContract.View{

    @BindView(R.id.toolbar)Toolbar toolbar;

    private ConfirmPasswordPresenter presenter ;

    public static ConfirmPasswordFragment newInstance() {
        ConfirmPasswordFragment fragment = new ConfirmPasswordFragment();
        return fragment;
    }

    private void setToolBar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("แก้ไขรหัสผ่าน");
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
    protected void initializePresenter() {
        presenter = new ConfirmPasswordPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        setToolBar();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_confirm_password;
    }

    @Override
    public void setChangePasswordSuccess() {

    }

    @OnClick({R.id.buttonOk})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonOk :
                presenter.sentChangePassword();
                break;
        }
    }

}
