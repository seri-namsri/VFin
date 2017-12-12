package com.mvision.vfin.component.financehistory;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.WalletTransectionResponseModel;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class FinanceHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private WalletTransectionResponseModel walletTransectionResponseModel;

    public FinanceHistoryAdapter(WalletTransectionResponseModel walletTransectionResponseModel) {
        this.walletTransectionResponseModel = walletTransectionResponseModel;
    }

    public void addWalletTransecTion(WalletTransectionResponseModel walletTransectionResponseModel) {
        checkLoading();
        this.walletTransectionResponseModel.result.addAll(walletTransectionResponseModel.result);
        notifyItemRangeInserted(this.walletTransectionResponseModel.result.size() + 1, walletTransectionResponseModel.result.size());
    }
    public void checkLoading(){
        if (this.walletTransectionResponseModel.result.get(this
                .walletTransectionResponseModel.result.size()-1) == null){
            this.walletTransectionResponseModel.result.remove(this
                    .walletTransectionResponseModel.result.size()-1);
            notifyItemRemoved(this.walletTransectionResponseModel.result.size());
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .adapter_finance_history, parent, false));
            case 2:
                return new ViewHolderLoading(LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .adapter_load_more, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textViewName.setText(walletTransectionResponseModel.result.get(position).note);
            viewHolder.textViewType.setText(walletTransectionResponseModel.result.get(position)
                    .getChannel());
            //    holder.textViewBy.setText("ขายให้ : "+financeHistoryModels.get(position).getBy());
            viewHolder.textViewPrice.setText(Html.fromHtml(walletTransectionResponseModel.result.get(position)
                    .getAmount()));
            viewHolder.textViewDate.setText(walletTransectionResponseModel.result.get(position).getTransactionDate());

        } catch (ClassCastException e) {
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (walletTransectionResponseModel.result.get(position)!=null) ? 1 : 2;
    }


    @Override
    public int getItemCount() {
        return walletTransectionResponseModel.result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewType)
        TextView textViewType;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewBy)
        TextView textViewBy;
        @BindView(R.id.textViewPrice)
        TextView textViewPrice;
        @BindView(R.id.textViewDate)
        TextView textViewDate;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public class ViewHolderLoading extends RecyclerView.ViewHolder {

        public ViewHolderLoading(View itemView) {
            super(itemView);
        }
    }
}
