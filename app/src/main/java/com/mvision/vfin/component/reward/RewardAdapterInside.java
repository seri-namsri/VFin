package com.mvision.vfin.component.reward;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */

public class RewardAdapterInside extends RecyclerView.Adapter<RewardAdapterInside.ViewHolder>{
    private ArrayList<RewardModel>rewardModelArrayList ;
    private CallBackClick clickItemReward ;
    public RewardAdapterInside(ArrayList<RewardModel>rewardModelArrayList,CallBackClick clickItemReward){
        this.rewardModelArrayList = rewardModelArrayList;
        this.clickItemReward = clickItemReward;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_reward, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewName.setText(rewardModelArrayList.get(position).getName());
        holder.textViewCoin.setText(rewardModelArrayList.get(position).getPrice()+ "");
        Glide.with(Contextor.getInstance().getContext()).load(rewardModelArrayList.get(position)
                .getImage_product().get(0))
                .into(holder.imageViewProduct);
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemReward.clickItemReward(rewardModelArrayList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return rewardModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewCoin)
        TextView textViewCoin;
        @BindView(R.id.buttonDetail)
        Button buttonDetail;
        @BindView(R.id.imageViewProduct)
        ImageView imageViewProduct;


        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemReward(RewardModel rewardModel);
    }

}
