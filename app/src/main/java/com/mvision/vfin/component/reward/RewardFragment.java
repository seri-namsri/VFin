package com.mvision.vfin.component.reward;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.reward.pojo.ModelRewardMerge;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.component.rewarddetail.RewardDetailActivity;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class RewardFragment extends BaseFragment implements RewardContract.View{

    @BindView(R.id.progress)ProgressBar progressBar;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    private RewardPresenter presenter ;

    public static RewardFragment newInstance() {
        RewardFragment fragment = new RewardFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        try {
            progressBar.setVisibility(View.GONE);
        }catch (NullPointerException e){}
    }


    @Override
    protected void initializePresenter() {
        presenter = new RewardPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getReward();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_reward;
    }

    @Override
    public void setUpViewReward(ArrayList<RewardModel> modelList) {
        try {
            RewardAdapterInside rewardAdapter = new RewardAdapterInside(modelList, new
                    RewardAdapterInside
                    .CallBackClick() {
                @Override
                public void clickItemReward(RewardModel rewardModel) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("reward", Parcels.wrap(rewardModel));
                    startActivityFromFragment(RewardDetailActivity.class,bundle);
                }
            });
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(rewardAdapter);
        }catch (Exception e){}

    }
}
