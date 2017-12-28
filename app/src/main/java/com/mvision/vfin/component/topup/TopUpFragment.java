package com.mvision.vfin.component.topup;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

/**
 * Created by enter_01 on 12/24/2017 AD.
 */

public class TopUpFragment extends BaseFragment {


    public static TopUpFragment newInstance() {
        TopUpFragment fragment = new TopUpFragment();
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

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_topup;
    }
}
