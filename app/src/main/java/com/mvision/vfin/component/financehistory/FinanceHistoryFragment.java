package com.mvision.vfin.component.financehistory;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.WalletTransectionResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryFragment extends BaseFragment implements FinanceHistoryContract.View{

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.textViewCoin)TextView textViewCoin;
    @BindView(R.id.recyclerViewHistoryFinance)RecyclerView recyclerViewHistoryFinance;
    private FinanceHistoryPresenter presenter ;
    private LinearLayoutManager linearLayoutManager ;
    private FinanceHistoryAdapter financeHistoryAdapter;
    public static FinanceHistoryFragment newInstance() {
        FinanceHistoryFragment fragment = new FinanceHistoryFragment();
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
     presenter = new FinanceHistoryPresenter(this);
     super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getCoin();
        presenter.getFinanceHistory();
        setUpToolbar();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_finance_history;
    }

    private void setUpToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ประวัติคะแนน");
    }

    @Override
    public void setUpViewFinanceHistory(WalletTransectionResponseModel walletTransectionResponseModel) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewHistoryFinance.setLayoutManager(linearLayoutManager);
        financeHistoryAdapter = new FinanceHistoryAdapter(walletTransectionResponseModel);
        recyclerViewHistoryFinance.setAdapter(financeHistoryAdapter);
        recyclerViewHistoryFinance.addOnScrollListener(onScrollListener);
    }

    @Override
    public void setUpViewFinanceHistoryMore(WalletTransectionResponseModel walletTransectionResponseModel) {
        loading = true;
       financeHistoryAdapter.addWalletTransecTion(walletTransectionResponseModel);
    }

    @Override
    public void setUpCoin(String coin) {
        textViewCoin.setText(coin+"");
    }

    @Override
    public void setUpViewFinanceHistoryLoading() {
        financeHistoryAdapter.checkLoading();
    }


    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;


   private RecyclerView.OnScrollListener onScrollListener =   new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) //check for scroll down
            {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        presenter.getFinanceHistoryMore();
                    }
                }
            }
        }

    };


    @Override
    public void onStop() {
        super.onStop();
        presenter.stopRealTime();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.stopRealTime();
    }
}
