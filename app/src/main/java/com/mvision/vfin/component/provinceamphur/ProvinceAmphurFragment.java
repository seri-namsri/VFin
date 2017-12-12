package com.mvision.vfin.component.provinceamphur;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import butterknife.BindView;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public class ProvinceAmphurFragment extends BaseFragment implements ProvinceAmphurContract.View {


    private ProvinceAmphurPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static ProvinceAmphurFragment newInstance(Bundle bundle) {
        ProvinceAmphurFragment fragment = new ProvinceAmphurFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        presenter = new ProvinceAmphurPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        setUptoolBar();
        presenter.getProvinceOrAmphur();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.android_adapter_reclycleview_dialog;
    }


    private void setUptoolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("เลือกจังหวัด");
    }

    @Override
    public void setProvince(ProvinceResponseModel provinceResponseModel) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ProvinceAmphurAdapter provinceAmphurAdapter = new ProvinceAmphurAdapter(provinceResponseModel, onClick);
        recyclerView.setAdapter(provinceAmphurAdapter);
    }

    @Override
    public void sentProvince(Province province) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("province", Parcels.wrap(province));
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @Override
    public void setAmphur(AmphurResponseModel amphurResponseModel) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ProvinceAmphurAdapter provinceAmphurAdapter = new ProvinceAmphurAdapter(amphurResponseModel, onClick);
        recyclerView.setAdapter(provinceAmphurAdapter);
    }

    @Override
    public void sentAmphur(Amphur amphur) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("amphur", Parcels.wrap(amphur));
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }


    private ProvinceAmphurAdapter.OnClick onClick = new ProvinceAmphurAdapter.OnClick() {
        @Override
        public void proVince(Province province) {

            presenter.clickProvince(province);
        }

        @Override
        public void amphur(Amphur amphur) {
            presenter.clickAmphur(amphur);
        }
    };
}
