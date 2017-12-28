package com.mvision.vfin.component.rewarddetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.RewardDetailResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.financehistory.FinanceHistoryActivity;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.myaddress.MyAddressActivity;
import com.mvision.vfin.component.myproduct.myproductmain.MyProductMainActivity;
import com.mvision.vfin.component.myproduct.myproductmain.MyProductMainFragment;
import com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice.DialogCalculatePriceDialogFragment;
import com.mvision.vfin.component.productdetail.alertdetail.AlertDetailFragment;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert.RewardDetailBuyDialogFragment;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;

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
    @BindView(R.id.buttonCoin)
    Button buttonCoin;
    @BindView(R.id.buttonBit)
    TextView buttonBit;
    private RewardDetailPresenter presenter;

    public static RewardDetailFragment newInstance(Bundle bundle) {
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
        presenter.getCoin();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_reward_detail;
    }


    @Override
    public void showRewardDetail(RewardDetailResponseModel rewardModel) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(rewardModel.result.name);
        textViewDetail.setText(rewardModel.result.briefDetails);
        buttonCoin.setText(rewardModel.result.currentPrice + "");
        RewardDetailPager rewardDetailPager = new RewardDetailPager(rewardModel.result.images);
        viewPager.setAdapter(rewardDetailPager);
        indicator.setViewPager(viewPager);
    }

    private RewardDetailBuyDialogFragment rewardDetailBuyDialogFragment;

    @Override
    public void showRewardDetailBuy(RewardModel rewardModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("RewardDetail", Parcels.wrap(rewardModel));
        rewardDetailBuyDialogFragment = RewardDetailBuyDialogFragment.newInstance(bundle, callBackRewardDetailBuyOnclick);
        rewardDetailBuyDialogFragment.show(getFragmentManager(), "dialog");

    }

    private CallBackRewardDetailBuyOnclick callBackRewardDetailBuyOnclick = new CallBackRewardDetailBuyOnclick() {
        @Override
        public void clickCancle() {

        }

        @Override
        public void clickOk() {
            presenter.gotoAddress();
        }
    };

    public interface CallBackRewardDetailBuyOnclick {
        void clickCancle();

        void clickOk();
    }


    @Override
    public void showFullDetail(String detail) {
        AlertDetailFragment editNameDialogFragment = AlertDetailFragment.newInstance(detail);
        editNameDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void setCoin(ModelCoinAndBit modelCoinAndBit) {
        buttonBit.setText(modelCoinAndBit.getWallet() + "");
    }

    @Override
    public void showAddress(int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putInt("code", 1);
        startActivityResultFromFragment(MyAddressActivity.class, bundle,
                requestCode);
    }

    @Override
    public void showCalculatePrice(Bundle bundle) {
        DialogCalculatePriceDialogFragment dialogCalculatePriceDialogFragment = DialogCalculatePriceDialogFragment
                .newInstance(bundle, callBackMangePagerTrad);
        dialogCalculatePriceDialogFragment.show(getFragmentManager(), "dialog");
    }

    private  MyProductMainFragment.CallBackMangePager callBackMangePagerTrad = new MyProductMainFragment.CallBackMangePager() {
        @Override
        public void reLoad(int position) {

        }

        @Override
        public void changePage(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("changePage",1);
            Intent intent = new Intent(Contextor.getInstance().getContext(), MyProductMainActivity.class);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Contextor.getInstance().getContext().startActivity(intent);
        }

        @Override
        public void startWalletHistory() {
            Intent intent = new Intent(Contextor.getInstance().getContext(), FinanceHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Contextor.getInstance().getContext().startActivity(intent);
        }

    };

    @Override
    public void hideDialogRewardDetailBuy() {
        if (rewardDetailBuyDialogFragment != null)
            rewardDetailBuyDialogFragment.dismiss();
    }

    @OnClick({R.id.buttonBit, R.id.buttonCoin, R.id.buttonProductDetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCoin:
                presenter.getRewardDetailBuy();
                break;
            case R.id.buttonProductDetail:
                presenter.getFullDetail();
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stopRealTime();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode)
            presenter.getActivityResult(requestCode, data);
    }
}
