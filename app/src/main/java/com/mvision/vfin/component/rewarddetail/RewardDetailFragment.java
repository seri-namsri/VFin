package com.mvision.vfin.component.rewarddetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert.RewardDetailBuyDialogFragment;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailFragment extends BaseFragment implements RewardDetailContract.View {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewDetail)
    TextView textViewDetail;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.design_bottom_sheet)
    View design_bottom_sheet;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.buttonBuy)
    Button buttonBuy;
    private RewardDetailPresenter presenter ;

    public static  RewardDetailFragment newInstance(Bundle bundle) {
        RewardDetailFragment fragment = new RewardDetailFragment();
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
         presenter = new RewardDetailPresenter(this);
         super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getRewardDetail();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_reward_detail;
    }


    @Override
    public void showRewardDetail(RewardModel rewardModel) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(rewardModel.getName());
        textViewDetail.setText(rewardModel.getDetail());
        RewardDetailPager rewardDetailPager = new RewardDetailPager(rewardModel.getImage_product());
        viewPager.setAdapter(rewardDetailPager);
        indicator.setViewPager(viewPager);
        buttonBuy.setText(rewardModel.getPrice()+"");
    }

    @Override
    public void showRewardDetailBuy(RewardModel rewardModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("RewardDetail", Parcels.wrap(rewardModel));
        RewardDetailBuyDialogFragment editNameDialogFragment = RewardDetailBuyDialogFragment.newInstance(bundle);
        editNameDialogFragment.show(getFragmentManager(), "dialog");
    }

    @OnClick({R.id.buttonBuy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonBuy :
                presenter.getRewardDetailBuy();
                break;
        }
    }



}
