package com.example.enter_01.vfin.component.productdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.component.profiledetail.AdapterAddress;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.ConvertDate;

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

    private List<MemberBuy>memberArrayList ;

    public ProductDetailAdapter(List<MemberBuy> memberArrayList){
         this.memberArrayList = memberArrayList ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductDetailAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_his_buy_product, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           holder.textViewUsername.setText(memberArrayList.get(position).getUsername().substring(0,4)+"xxxxx");
           holder.textViewPrice.setText(memberArrayList.get(position).getPrice()+"");
           holder.textViewDate.setText(ConvertDate.getInstance().TimestampToFormatDateAndTimeTH(memberArrayList.get(position)
                   .getDate_buy()+"","dd MMM yyyy HH:mm น."));

        CropCircleTransformation multi = new CropCircleTransformation();
        Glide.with(Contextor.getInstance().getContext())
                .load(memberArrayList.get(position)
                        .getImageProfile()).apply(bitmapTransform(multi))
                .into(holder.imageViewProfile);
    }

    @Override
    public int getItemCount() {
        return memberArrayList.size();
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
