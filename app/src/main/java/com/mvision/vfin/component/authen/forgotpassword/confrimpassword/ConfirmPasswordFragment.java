package com.mvision.vfin.component.authen.forgotpassword.confrimpassword;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.customview.EditTextWithFont;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ConfirmPasswordFragment extends BaseFragment implements ConfirmPasswordContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editTextPassword)
    EditTextWithFont editTextPassword;
    @BindView(R.id.editTextConfirmPassword)
    EditTextWithFont editTextConfirmPassword;

    private ConfirmPasswordPresenter presenter;

    public static ConfirmPasswordFragment newInstance(Bundle bundle) {
        ConfirmPasswordFragment fragment = new ConfirmPasswordFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void setToolBar() {
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
        showProgress(getString(R.string.textLoading));
    }

    @Override
    public void hideLoading() {
        dismissProgress();
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
        getActivity().finish();
    }

    @OnClick({R.id.buttonOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                presenter.sentChangePassword(editTextPassword.getTextDataNotNull(null),
                        editTextConfirmPassword.getTextDataNotNull(null));
                break;
        }
    }

}
