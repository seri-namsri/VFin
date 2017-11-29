package com.mvision.vfin.component.leveldetail;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class LevelDetailFragment extends BaseFragment implements LevelDetailContract.View{

    public static LevelDetailFragment newInstance() {
        LevelDetailFragment fragment = new LevelDetailFragment();
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
    public void setUpView() {

    }


    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.activity_base;
    }
}
