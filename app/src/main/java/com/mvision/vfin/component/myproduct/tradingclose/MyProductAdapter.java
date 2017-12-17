package com.mvision.vfin.component.myproduct.tradingclose;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.utility.Contextor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 12/8/2017 AD.
 */

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolder>{

    private OnClickMyProduct onClickMyproduct ;
    private MyProductResponseModel myProductResponseModel ;
    public MyProductAdapter(MyProductResponseModel myProductResponseModel, OnClickMyProduct onClickMyproduct){
        this.onClickMyproduct = onClickMyproduct;
        this.myProductResponseModel = myProductResponseModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_my_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewName.setText(myProductResponseModel.result.get(position).name);
        holder.textViewDetail.setText("ราคาซื้อ : "+myProductResponseModel.result.get(position)
                .currentPrice+"\n"+"น้ำหนัก : "+myProductResponseModel.result.get(position)
                .weight);
        Glide.with(Contextor.getInstance().getContext()).load(myProductResponseModel.result.get
                (position).imgUrl).into(holder.imageViewProduct);
             holder.buttonClick.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onClickMyproduct.onclickItem(position);
                 }
             });
    }

    @Override
    public int getItemCount() {
        return myProductResponseModel.result.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)TextView textViewName;
        @BindView(R.id.textViewOrder)TextView textViewOrder;
        @BindView(R.id.imageViewProduct)ImageView imageViewProduct;
        @BindView(R.id.textViewAddressName)TextView textViewAddressName;
        @BindView(R.id.textViewAddressDetail)TextView textViewAddressDetail;
        @BindView(R.id.textViewDetail)TextView textViewDetail;
        @BindView(R.id.buttonClick)Button buttonClick;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickMyProduct{
        void onclickItem(int position);
    }
}
