package com.example.enter_01.vfin.component.myaddress;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.api.response.MyAddressResponseModel;
import com.example.enter_01.vfin.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressFragment extends BaseFragment implements MyAddressContract.View{

    @BindView(R.id.recyclerView)RecyclerView recyclerView;

    private MyAddressPresenter presenter ;
    public static MyAddressFragment newInstance() {
        MyAddressFragment fragment = new MyAddressFragment();
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
    public void setUpViewAddress(MyAddressResponseModel myAddressResponseModel) {
        MyAddressAdapter myAddressAdapter = new MyAddressAdapter(myAddressResponseModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAddressAdapter);
    }

    @Override
    protected void initializePresenter() {
        presenter = new MyAddressPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getAddress();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_address;
    }
}
