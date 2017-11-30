package com.mvision.vfin.component.myaddress;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.adddress.AddAddressActivity;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressFragment extends BaseFragment implements MyAddressContract.View{

    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    @BindView(R.id.toolbar)Toolbar toolbar;

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


    private void setUptoolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("เลือกที่อยู่");
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
        setUptoolBar();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_address, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addAddress:
                startActivityFromFragment(AddAddressActivity.class,null);
               // startActivityForResult(new Intent(this,InterestActivity.class),1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_address;
    }
}
