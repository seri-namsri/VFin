package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.myaddress.MyAddressActivity;
import com.mvision.vfin.utility.Log;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class TradingCloseFragment extends BaseFragment implements TradingCloseContract.View {

    @BindView(R.id.recyclerView)RecyclerView recyclerView ;
    private TradingClosePresenter presenter ;
    private  MyProductAdapter myProductAdapter ;
    public static TradingCloseFragment newInstance(Bundle bundle) {
        TradingCloseFragment fragment = new TradingCloseFragment();
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
        presenter = new TradingClosePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getMyProduct();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_product_process;
    }


    @Override
    public void setUpViewMyproduct(MyProductResponseModel myProductResponseModel) {
        Log.e("setUpViewMyproduct",new Gson().toJson(myProductResponseModel));
        myProductAdapter = new MyProductAdapter(myProductResponseModel,onClickMyProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myProductAdapter);
    }

    @Override
    public void gotoAdddress(int requestCode) {
        startActivityResultFromFragment(MyAddressActivity.class, null, requestCode);
    }

    @Override
    public void gotoBuyProduct() {

    }

    @Override
    public void gotoBuyProductFail() {

    }

    @Override
    public void gotoSentProduct() {

    }

    @Override
    public void setViewUpdateAddress() {

    }

    private MyProductAdapter.OnClickMyProduct onClickMyProduct = new MyProductAdapter.OnClickMyProduct() {
        @Override
        public void onclickItem(int position) {

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getActivityResult(requestCode, data);
    }

}
