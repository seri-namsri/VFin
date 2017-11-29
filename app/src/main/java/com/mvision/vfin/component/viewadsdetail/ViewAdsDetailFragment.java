package com.mvision.vfin.component.viewadsdetail;

import android.widget.VideoView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsDetailFragment extends BaseFragment {

    @BindView(R.id.videoViewAds)VideoView videoView ;

    public static ViewAdsDetailFragment newInstance(){
        ViewAdsDetailFragment fragment = new ViewAdsDetailFragment();
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

    }

    @Override
    protected void startView() {
        videoView.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4");
        videoView.start();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_view_ads_detail;
    }
}
