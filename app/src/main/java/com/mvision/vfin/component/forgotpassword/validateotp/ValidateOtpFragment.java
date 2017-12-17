package com.mvision.vfin.component.forgotpassword.validateotp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.forgotpassword.confrimpassword.ConfirmPasswordActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ValidateOtpFragment extends BaseFragment {
    @BindView(R.id.toolbar)Toolbar toolbar;
    public static ValidateOtpFragment newInstance() {
        ValidateOtpFragment fragment = new ValidateOtpFragment();
        return fragment;
    }

    @OnClick({R.id.buttonOk})
    public void onClick(View view){
        startActivityFromFragment(ConfirmPasswordActivity.class,null);
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

    }

    @Override
    protected void startView() {
        setToolBar();
    }

    private void setToolBar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ยืนยันตัวตน OTP");
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_validate_otp;
    }
}
