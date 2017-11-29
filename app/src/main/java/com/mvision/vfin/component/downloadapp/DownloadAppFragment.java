package com.mvision.vfin.component.downloadapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.downloadapp.pojo.DownloadAppModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class DownloadAppFragment extends BaseFragment implements DownloadAppContract.View{

    @BindView(R.id.recyclerView)RecyclerView recyclerView;


    private DownloadAppPresenter presenter ;

    public static  DownloadAppFragment newInstance(){
        DownloadAppFragment fragment = new DownloadAppFragment();
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
        presenter = new DownloadAppPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getDownloadApp();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_downloadapp;
    }

    @Override
    public void setUpViewDownloadApp(ArrayList<DownloadAppModel> downloadAppModels) {
        DownloadAppAdapter appAdapter = new DownloadAppAdapter(downloadAppModels, new DownloadAppAdapter.CallBackClick() {
            @Override
            public void clickItemMessage(DownloadAppModel downloadAppModel) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google" +
                        ".com/store/apps/details?id="+downloadAppModel.getPaketname()));
                startActivity(i);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(appAdapter);
    }
}
