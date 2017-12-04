package com.mvision.vfin.component.myaddress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.mvision.vfin.component.addeditdress.AddEditAddressActivity;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.Utility;

import org.parceler.Parcels;

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
        Utility.ShowMsg(getActivity(),msg);
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



    private MyAddressAdapter myAddressAdapter ;
    @Override
    public void setUpViewAddress(MyAddressResponseModel myAddressResponseModel) {
        myAddressAdapter = new MyAddressAdapter(myAddressResponseModel, new MyAddressAdapter.OnClickItem() {
            @Override
            public void itemClickEdit(AddressModel addressModel,int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("addressModel", Parcels.wrap(addressModel));
                bundle.putInt("positionAddress", position);
                startActivityResultFromFragment(AddEditAddressActivity.class,bundle,2);
            }

            @Override
            public void itemClickSelect(AddressModel addressModel,int position) {
                myAddressAdapter.setClickPosition(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAddressAdapter);
    }

    @Override
    public void updateDataAddress() {
        myAddressAdapter.notifyDataSetChanged();
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
                startActivityResultFromFragment(AddEditAddressActivity.class,null,1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            presenter.getUpdateAddress(requestCode,data);
        }

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_address;
    }
}
