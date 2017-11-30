package com.mvision.vfin.component.adddress;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseActivity;
import com.mvision.vfin.customview.EditTextWithFont;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.textViewProvince)
    TextView textViewProvince;
    @BindView(R.id.textViewDistrict)
    TextView textViewDistrict;
    @BindView(R.id.textViewSubDistrict)
    TextView textViewSubDistrict;
    @BindView(R.id.editTextPostalCode)
    EditTextWithFont editTextPostalCode;
    @BindView(R.id.editTextStreet)
    EditTextWithFont editTextStreet;
    @BindView(R.id.editTextSoi)
    EditTextWithFont editTextSoi;
    @BindView(R.id.editTextNo)
    EditTextWithFont editTextNo;
    @BindView(R.id.editTextName)
    EditTextWithFont editTextName;
    @BindView(R.id.editTextNameAddress)
    EditTextWithFont editTextNameAddress;


    @OnClick({R.id.buttonOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                break;
            case R.id.textViewProvince:
                break;
            case R.id.textViewDistrict:
                break;
            case R.id.textViewSubDistrict:
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
    protected int setLayoutResourceIdentifier() {
        return R.layout.activity_base;
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void startView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, AddAddressFragment.newInstance());
        transaction.commit();
    }
}
