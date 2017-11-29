package com.mvision.vfin.component.viewads;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.viewads.pojo.ViewAdsModel;
import com.mvision.vfin.component.viewadsdetail.ViewAdsDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsFragment extends BaseFragment implements ViewAdsContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)Toolbar toolbar;
    private ViewAdsPresenter presenter;

    public static ViewAdsFragment newInstance() {
        ViewAdsFragment fragment = new ViewAdsFragment();
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
        presenter = new ViewAdsPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getViewAds();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_view_ads;
    }

    @Override
    public void setUpViewAds(ArrayList<ViewAdsModel> ads) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("กิจกรรม");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewAdsAdapter viewAdsAdapter = new ViewAdsAdapter(ads, new ViewAdsAdapter.CallBackClick() {
            @Override
            public void clickItemProduct(ViewAdsModel viewAdsModel) {

                startActivityFromFragment(ViewAdsDetailActivity.class,null);

            }

        });
        recyclerView.setAdapter(viewAdsAdapter);
    }
}
