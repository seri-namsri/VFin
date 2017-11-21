package com.example.enter_01.vfin.component.reward;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;
import com.example.enter_01.vfin.component.reward.pojo.ModelRewardMerge;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;
import com.example.enter_01.vfin.component.rewarddetail.RewardDetailActivity;
import com.example.enter_01.vfin.utility.Log;
import com.google.gson.Gson;

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
    public void setUpViewReward(ArrayList<ModelRewardMerge> modelList) {
        RewardAdapter rewardAdapter = new RewardAdapter(modelList, new RewardAdapterInside.CallBackClick() {
            @Override
            public void clickItemReward(RewardModel rewardModel) {
                Bundle bundle = new Bundle();
                bundle.putString("reward_id",rewardModel.getReward_id());
                startActivityFromFragment(RewardDetailActivity.class,bundle);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rewardAdapter);
    }
}
