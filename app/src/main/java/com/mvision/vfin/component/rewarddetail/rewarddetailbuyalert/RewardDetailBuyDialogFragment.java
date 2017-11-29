package com.mvision.vfin.component.rewarddetail.rewarddetailbuyalert;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseDialogFragment;
import com.mvision.vfin.component.reward.pojo.RewardModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/16/2017 AD.
 */

public class RewardDetailBuyDialogFragment extends BaseDialogFragment implements
        RewardDetailBuyDialogContract.View {

    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewPrice)
    TextView textViewPrice;

    private RewardDetailBuyDialogPresenter presenter;


    public static RewardDetailBuyDialogFragment newInstance(Bundle bundle) {
        RewardDetailBuyDialogFragment fragment = new RewardDetailBuyDialogFragment();
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
    public void showRewardDetailBuy(RewardModel rewardModel) {
        textViewName.setText(rewardModel.getName());
        textViewPrice.setText(rewardModel.getPrice() + "");
    }

    @Override
    protected void initializePresenter() {
        presenter = new RewardDetailBuyDialogPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getRewardDetailBuy();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.dialog_fragment_buy_reward;
    }

    @OnClick({R.id.buttonCancel, R.id.buttonOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCancel:
                dismiss();
                break;
            case R.id.buttonOk:
                dismiss();
                break;
        }
    }
}
