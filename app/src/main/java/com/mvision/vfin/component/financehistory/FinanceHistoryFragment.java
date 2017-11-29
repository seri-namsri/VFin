package com.mvision.vfin.component.financehistory;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryFragment extends BaseFragment implements FinanceHistoryContract.View{

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.recyclerViewHistoryFinance)RecyclerView recyclerViewHistoryFinance;
    private FinanceHistoryPresenter presenter ;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ประวัตการเงิน");
    }

    @Override
    public void setUpViewFinanceHistory(ArrayList<FinanceHistoryModel> viewFinanceHistory) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewHistoryFinance.setLayoutManager(linearLayoutManager);
        FinanceHistoryAdapter financeHistoryAdapter = new FinanceHistoryAdapter(viewFinanceHistory);
        recyclerViewHistoryFinance.setAdapter(financeHistoryAdapter);
    }
}
