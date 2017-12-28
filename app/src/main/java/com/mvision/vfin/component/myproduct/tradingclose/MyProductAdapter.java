package com.mvision.vfin.component.myproduct.tradingclose;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
        holder.textViewOrder.setText("Order ID : ");
        if (myProductResponseModel.result.get(position).status.equals("closed")){
            holder.buttonClick.setVisibility(View.VISIBLE);
            holder.buttonClick.setText("เลือกที่อยู่จัดส่งและชำระสินค้า");
            holder.textViewStatusDetail.setVisibility(View.VISIBLE);
            holder.textViewDetailPrice.setVisibility(View.VISIBLE);
            holder.textViewEmsTitle.setVisibility(View.GONE);
            holder.textViewEms.setVisibility(View.GONE);
            holder.textViewStatus.setText("สถานะ : รอชำระค่าจัดส่ง");
            holder.viewLine.setVisibility(View.GONE);
        }else if (myProductResponseModel.result.get(position).status.equals("fee_paid")){
            holder.buttonClick.setVisibility(View.GONE);
            holder.textViewStatusDetail.setVisibility(View.GONE);
            holder.textViewDetailPrice.setVisibility(View.GONE);
            holder.textViewStatus.setText("สถานะ : เตรียมจัดส่ง");
            holder.textViewEmsTitle.setVisibility(View.GONE);
            holder.textViewEms.setVisibility(View.GONE);
        }else if (myProductResponseModel.result.get(position).status.equals("delivery")){
            holder.buttonClick.setVisibility(View.GONE);
            holder.textViewStatusDetail.setVisibility(View.GONE);
            holder.textViewDetailPrice.setVisibility(View.GONE);
            holder.textViewStatus.setText("สถานะ : ส่งเรียบร้อบแล้ว");
        }


        Glide.with(Contextor.getInstance().getContext()).load(myProductResponseModel.result.get
                (position).imgUrl).into(holder.imageViewProduct);
             holder.buttonClick.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onClickMyproduct.onclickItem(position);
                 }
             });
             try {
                 holder.textViewAddress.setText(Html.fromHtml(myProductResponseModel.result.get(position)
                         .address.getAddressAll()));
                 holder.textViewDetailPrice.setVisibility(View.GONE);
             }catch (NullPointerException e){
                 holder.textViewAddress.setVisibility(View.GONE);
                 holder.textViewDetailPrice.setVisibility(View.VISIBLE);
                 holder.textViewDetailPrice.setText("หากไม่ดำเนินการชำระค่าจัดส่งภายในเวลาที่กำหนด " +
                         "เราจะนำสินค้ากลับมาขายใหม่ในราคา "+(myProductResponseModel.result.get(position)
                         .currentPrice*80)/100 + " วินพอยต์ (หัก 20%)");
             }
         }

    @Override
    public int getItemCount() {
        return myProductResponseModel.result.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)TextView textViewName;
        @BindView(R.id.textViewOrder)TextView textViewOrder;
        @BindView(R.id.imageViewProduct)ImageView imageViewProduct;
        @BindView(R.id.textViewStatus)TextView textViewStatus;
        @BindView(R.id.textViewStatusDetail)TextView textViewStatusDetail;
        @BindView(R.id.textViewDetail)TextView textViewDetail;
        @BindView(R.id.textViewDetailPrice)TextView textViewDetailPrice;
        @BindView(R.id.textViewAddress)TextView textViewAddress;
        @BindView(R.id.textViewEmsTitle)TextView textViewEmsTitle;
        @BindView(R.id.textViewEms)TextView textViewEms;
        @BindView(R.id.buttonClick)Button buttonClick;
        @BindView(R.id.viewLine)View viewLine;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickMyProduct{
        void onclickItem(int position);
    }
}
