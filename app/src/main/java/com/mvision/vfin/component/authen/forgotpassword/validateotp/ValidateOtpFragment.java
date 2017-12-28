package com.mvision.vfin.component.authen.forgotpassword.validateotp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.forgotpassword.confrimpassword.ConfirmPasswordActivity;
import com.mvision.vfin.customview.EditTextWithFont;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ValidateOtpFragment extends BaseFragment implements ValidateOtpContract.View{
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.editTextOtp)EditTextWithFont editTextOtp;
    @BindView(R.id.textViewRef)TextView textViewRef;


    private ValidateOtpPresenter presenter ;
    public static ValidateOtpFragment newInstance(Bundle bundle) {
        ValidateOtpFragment fragment = new ValidateOtpFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.buttonOk})
    public void onClick(View view){
        presenter.verifyOtp(editTextOtp.getTextDataNotNull(null));
    }

    @Override
    public void showMessageFail(String msg) {

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
    protected void initializePresenter() {
        presenter = new ValidateOtpPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        setToolBar();
        presenter.getRefCode();
    }

    @OnClick({R.id.textViewResentOTP})
    public void onClickResentOTP(View view){
        presenter.getResentOtp();
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

    @Override
    public void verifySuccess(Bundle bundle) {
        getActivity().finish();
        startActivityFromFragment(ConfirmPasswordActivity.class,bundle);
    }

    @Override
    public void verifyRegisterSuccess(Bundle bundle) {
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @Override
    public void showRefCode(String refCode) {
        textViewRef.setText("Ref : "+refCode);
    }
}
