package com.mvision.vfin.component.myproduct.myproductprocess;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductProcessFragment extends BaseFragment implements MyProductProcessContract.View {

    @BindView(R.id.recyclerView)RecyclerView recyclerView ;
    private MyProductProcessPresenter presenter ;
    public static MyProductProcessFragment newInstance() {
        MyProductProcessFragment fragment = new MyProductProcessFragment();
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
        presenter = new MyProductProcessPresenter(this);
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
    public void setUpViewMyproduct() {
        MyProductAdapter myProductAdapter = new MyProductAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myProductAdapter);
    }
}
