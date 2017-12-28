package com.mvision.vfin.component.myproduct.tradingclose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.myaddress.MyAddressActivity;
import com.mvision.vfin.component.myproduct.myproductmain.MyProductMainFragment;
import com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice.DialogCalculatePriceDialogFragment;
import com.mvision.vfin.utility.Log;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class TradingCloseFragment extends BaseFragment implements TradingCloseContract.View {

    @BindView(R.id.recyclerView)RecyclerView recyclerView ;
    @BindView(R.id.textViewError)TextView textViewError ;
    @BindView(R.id.progressBar)ProgressBar progressBar ;
    private TradingClosePresenter presenter ;
    private  MyProductAdapter myProductAdapter ;
    private static MyProductMainFragment.CallBackMangePager callBackMangePagerTrad ;
    public static TradingCloseFragment newInstance(Bundle bundle,MyProductMainFragment.CallBackMangePager callBackMangePager) {
        if (callBackMangePagerTrad == null)
            callBackMangePagerTrad = callBackMangePager ;
        TradingCloseFragment fragment = new TradingCloseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {
        textViewError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void initializePresenter() {
        presenter = new TradingClosePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }



    public void start(){
        presenter.getMyProduct();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_product_process;
    }


    @Override
    public void setUpViewMyproduct(MyProductResponseModel myProductResponseModel) {
        textViewError.setVisibility(View.GONE);
        myProductAdapter = new MyProductAdapter(myProductResponseModel,onClickMyProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myProductAdapter);
    }

    @Override
    public void gotoAdddress(int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putInt("code",1);
        startActivityResultFromFragment(MyAddressActivity.class,bundle,
        requestCode);
    }

    @Override
    public void showDialogCalculatePrice(Bundle bundle) {
        DialogCalculatePriceDialogFragment dialogCalculatePriceDialogFragment = DialogCalculatePriceDialogFragment
                .newInstance(bundle,callBackMangePagerTrad);
        dialogCalculatePriceDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void notFoundData() {
        recyclerView.setVisibility(View.GONE);
        textViewError.setVisibility(View.VISIBLE);
    }


    private MyProductAdapter.OnClickMyProduct onClickMyProduct = new MyProductAdapter.OnClickMyProduct() {
        @Override
        public void onclickItem(int position) {
            presenter.getOnclickProduct(position);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getActivityResult(requestCode, data);
    }

}
