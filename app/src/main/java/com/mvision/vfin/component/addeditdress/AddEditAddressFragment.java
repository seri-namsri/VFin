package com.mvision.vfin.component.addeditdress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.customview.EditTextWithFont;
import com.mvision.vfin.customview.TextViewWithFont;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.Utility;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddEditAddressFragment extends BaseFragment implements AddEditAddressContract.View {

    @BindView(R.id.textViewProvince)
    TextViewWithFont textViewProvince;
    @BindView(R.id.textViewDistrict)
    TextViewWithFont textViewDistrict;
    @BindView(R.id.textViewSubDistrict)
    TextViewWithFont textViewSubDistrict;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @OnClick({R.id.buttonOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                presenter.getClickOk();
                break;
            case R.id.textViewProvince:
                presenter.getProvince();
                break;
            case R.id.textViewDistrict:
                presenter.getDistrict(1);
                break;
            case R.id.textViewSubDistrict:
                presenter.getSubDistrict(1);
                break;
        }
    }


    private AddEditAddressPresenter presenter;

    public static AddEditAddressFragment newInstance(Bundle bundle) {
        AddEditAddressFragment fragment = new AddEditAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
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
    protected void initializePresenter() {
        presenter = new AddEditAddressPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getEditAddress();

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_add_address;
    }

    @Override
    public void showProvince() {

    }

    @Override
    public void showDistrict() {

    }

    @Override
    public void showSubDistrict() {

    }

    @Override
    public void showSetUpEdit(AddressModel addressModel) {
        textViewProvince.setText(addressModel.getProvince());
        textViewDistrict.setText(addressModel.getDistrict());
        textViewSubDistrict.setText(addressModel.getSubDistrict());
        editTextPostalCode.setText(addressModel.getPostalCode());
        editTextStreet.setText(addressModel.getRoad());
        editTextSoi.setText(addressModel.getSoi());
        editTextNo.setText(addressModel.getHouseNo());
        editTextName.setText(addressModel.getReceiverName());
        editTextNameAddress.setText(addressModel.getName());
        setUptoolBar("แก้ไขที่อยู่");
    }

    @Override
    public void showSetUpAdd() {
        setUptoolBar("เพิ่มที่อยู่");
    }

    private void setUptoolBar(String title) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    @Override
    public void saveAddressSuccess(AddressModel addressModel,int positionAddress) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("addressModel", Parcels.wrap(addressModel));
        bundle.putInt("positionAddress", positionAddress);
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @Override
    public void clickOk(AddressModel addressModel) {
        addressModel.setDistrict(textViewDistrict.getTextDataNotNull(null));
        addressModel.setHouseNo(editTextNo.getTextDataNotNull(null));
        addressModel.setName(editTextNameAddress.getTextDataNotNull(null));
        addressModel.setProvince(textViewProvince.getTextDataNotNull(null));
        addressModel.setReceiverName(editTextName.getTextDataNotNull(null));
        addressModel.setPostalCode(editTextPostalCode.getTextDataNotNull(null));
        addressModel.setSubDistrict(textViewSubDistrict.getTextDataNotNull(null));
        addressModel.setRoad(editTextStreet.getTextDataNotNull(null));
        addressModel.setSoi(editTextSoi.getTextDataNotNull(null));
        addressModel.setIsPrimary("true");
        presenter.sentAddress(addressModel);
    }
}
