package com.mvision.vfin.component.myproduct;

import android.view.View;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.myaddress.MyAddressActivity;

import butterknife.OnClick;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductFragment extends BaseFragment {

    public static MyProductFragment newInstance() {
        MyProductFragment fragment = new MyProductFragment();
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
        return R.layout.fragment_my_product;
    }

    @OnClick({R.id.buttonAddress})
    public void  onClick(View view){
        startActivityFromFragment(MyAddressActivity.class,null);
    }
}
