package com.mvision.vfin.component.myproduct.myproductprocess;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvision.vfin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 12/8/2017 AD.
 */

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolder>{

    public MyProductAdapter(){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_my_producr, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)TextView textViewName;
        @BindView(R.id.textViewOrder)TextView textViewOrder;
        @BindView(R.id.imageViewProduct)ImageView imageViewProduct;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
