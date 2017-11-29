package com.mvision.vfin.component.productdetail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailPager extends PagerAdapter {
    private ArrayList<String> image ;
    private LayoutInflater mLayoutInflater;
    public ProductDetailPager (ArrayList<String> image){
        this.image = image;
        mLayoutInflater = (LayoutInflater) Contextor.getInstance().getContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return image.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_product_detail, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewProductDetail);


        Glide.with(Contextor.getInstance().getContext()).load(image.get(position)).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
