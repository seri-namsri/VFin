package com.mvision.vfin.component.productdetail.alertdetail;

import android.os.Bundle;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseDialogFragment;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class AlertDetailFragment extends BaseDialogFragment implements AlertDetailContract.View{

    @BindView(R.id.textViewDetail)TextView textViewDetail;

    private AlertDetailPresenter presenter ;

    public static AlertDetailFragment newInstance(String detail) {

        AlertDetailFragment frag = new AlertDetailFragment();
        Bundle args = new Bundle();
        args.putString("product_detail", detail);
        frag.setArguments(args);
        return frag;
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
        presenter = new AlertDetailPresenter(this);
        super.presenter = presenter ;
    }

    @Override
    protected void startView() {
        presenter.getProductDetail();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_alert_detail;
    }

    @Override
    public void setUpViewProductDetail(String detail) {

        textViewDetail.setText(detail);
    }
}
