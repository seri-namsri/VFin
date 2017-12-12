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
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;
import com.mvision.vfin.customview.EditTextWithFont;
import com.mvision.vfin.customview.TextViewWithFont;
import com.mvision.vfin.component.provinceamphur.ProvinceAmphurActivity;
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
    @BindView(R.id.textViewDetail)
    EditTextWithFont textViewDetail;
    @BindView(R.id.editTextPostalCode)
    EditTextWithFont editTextPostalCode;
    @BindView(R.id.editTextTel)
    EditTextWithFont editTextTel;
    @BindView(R.id.editTextNo)
    EditTextWithFont editTextNo;
    @BindView(R.id.editTextName)
    EditTextWithFont editTextName;
    @BindView(R.id.editTextNameAddress)
    EditTextWithFont editTextNameAddress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @OnClick({R.id.buttonOk,R.id.textViewProvince,R.id.textViewDistrict})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                presenter.getClickOk();
                break;
            case R.id.textViewProvince:
                presenter.getProvince();
                break;
            case R.id.textViewDistrict:
                presenter.getDistrict();
                break;
            case R.id.textViewDetail:
                presenter.getSubDistrict();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            presenter.getDataResult(requestCode,data);
    }


    @Override
    public void showProvince() {
        Bundle bundle = new Bundle();
        bundle.putInt("keyCode",1);
        startActivityResultFromFragment(ProvinceAmphurActivity.class,bundle,1);
    }

    @Override
    public void setClickProvince(Province province) {
        textViewProvince.setText(province.provinceName);
    }

    @Override
    public void setClickAmphur(String amphur) {
        textViewDistrict.setText(amphur);
    }

    @Override
    public void showDistrict(int idProvince) {
        Bundle bundle = new Bundle();
        bundle.putInt("keyCode",2);
        bundle.putInt("provinceId",idProvince);
        startActivityResultFromFragment(ProvinceAmphurActivity.class,bundle,2);
    }

    @Override
    public void showSubDistrict() {

    }

    @Override
    public void showSetUpEdit(AddressModel addressModel) {
        textViewProvince.setText(addressModel.province.provinceName);
        textViewDistrict.setText(addressModel.amphur.amphurName);
        textViewDetail.setText(addressModel.details);
        editTextPostalCode.setText(addressModel.postalCode);
        editTextTel.setText(addressModel.telNo);
        editTextNo.setText(addressModel.houseNo);
        editTextName.setText(addressModel.receiver);
        editTextNameAddress.setText(addressModel.name);
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
      //  addressModel.setProvince(textViewProvince.getTextDataNotNull(null));
        addressModel.setReceiverName(editTextName.getTextDataNotNull(null));
        addressModel.setPostalCode(editTextPostalCode.getTextDataNotNull(null));
        addressModel.setDetails(textViewDetail.getTextDataNotNull(null));
        addressModel.setTelNo(editTextTel.getTextDataNotNull(null));
        addressModel.setIsPrimary("no");
        presenter.sentAddress(addressModel);
    }
}
