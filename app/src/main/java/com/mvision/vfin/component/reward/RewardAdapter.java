package com.mvision.vfin.component.reward;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.component.reward.pojo.ModelRewardMerge;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder>{
    private ArrayList<ModelRewardMerge>rewardModelArrayList ;
    private RewardAdapterInside.CallBackClick clickItemReward ;
    public RewardAdapter(ArrayList<ModelRewardMerge>rewardModelArrayList,RewardAdapterInside.CallBackClick clickItemReward){
        this.rewardModelArrayList = rewardModelArrayList;
        this.clickItemReward = clickItemReward;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_reward_head, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewName.setText(rewardModelArrayList.get(position).catName);
        RewardAdapterInside rewardAdapter = new RewardAdapterInside(rewardModelArrayList.get
                (position).rewardModels,clickItemReward);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Contextor.getInstance()
                .getContext(),LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(rewardAdapter);
    }

    @Override
    public int getItemCount() {
        return rewardModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;


        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemReward(RewardModel rewardModel);
    }

}
