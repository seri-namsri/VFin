package com.mvision.vfin.component.productdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.productdetail.pojo.MemberProductHistory;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.ConvertDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailAdapter extends  RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {

    private List<MemberProductHistory>memberProductHistory ;

    public ProductDetailAdapter(List<MemberProductHistory> memberProductHistory){
         this.memberProductHistory = memberProductHistory ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductDetailAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_his_buy_product, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           holder.textViewUsername.setText(memberProductHistory.get(position).getName());
           holder.textViewPrice.setText(memberProductHistory.get(position).getBuyPrice()+"");
           holder.textViewDate.setText(ConvertDate.getInstance().TimestampToFormatDateAndTimeTH
                   (memberProductHistory.get(position).getBuyTime()/1000+"","dd MMM yyyy HH:mm " +
                           "น."));

        CropCircleTransformation multi = new CropCircleTransformation();
        Glide.with(Contextor.getInstance().getContext())
                .load(memberProductHistory.get(position).getAvatarLink()).apply(bitmapTransform(multi))
                .into(holder.imageViewProfile);
    }

    @Override
    public int getItemCount() {
        return memberProductHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewUsername)
        TextView textViewUsername;
        @BindView(R.id.textViewPrice) TextView textViewPrice;
        @BindView(R.id.textViewDate) TextView textViewDate;
        @BindView(R.id.imageViewProfile)
        ImageView imageViewProfile;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
