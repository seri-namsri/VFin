package com.example.enter_01.vfin.component.financehistory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.financehistory.pojo.FinanceHistoryModel;
import com.example.enter_01.vfin.component.message.MessageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class FinanceHistoryAdapter extends RecyclerView.Adapter<FinanceHistoryAdapter.ViewHolder>{

    private ArrayList<FinanceHistoryModel>financeHistoryModels ;
    public FinanceHistoryAdapter(ArrayList<FinanceHistoryModel>financeHistoryModels){
        this.financeHistoryModels = financeHistoryModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_finance_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textViewName.setText(financeHistoryModels.get(position).getProduct_name());
            holder.textViewType.setText((financeHistoryModels.get(position).getType().equals
                    ("sell")? "ขาย":""));
            holder.textViewBy.setText("ขายให้ : "+financeHistoryModels.get(position).getBy());
            holder.textViewPrice.setText(financeHistoryModels.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return financeHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewType)TextView textViewType;
        @BindView(R.id.textViewName)TextView textViewName;
        @BindView(R.id.textViewBy)TextView textViewBy;
        @BindView(R.id.textViewPrice)TextView textViewPrice;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
