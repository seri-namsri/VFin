package com.example.enter_01.vfin.component.viewads;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.viewads.pojo.ViewAdsModel;
import com.example.enter_01.vfin.utility.Contextor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class ViewAdsAdapter extends RecyclerView.Adapter<ViewAdsAdapter.ViewHolder> {

    private ArrayList<ViewAdsModel> surveyModelArrayList;
    private CallBackClick callBackClick ;
    public ViewAdsAdapter(ArrayList<ViewAdsModel> surveyModelArrayList, CallBackClick callBackClick) {
        this.surveyModelArrayList = surveyModelArrayList;
        this.callBackClick = callBackClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_survey, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        int millis = (int) surveyModelArrayList.get(position).getDulation();
        holder.df.setTimeZone(holder.tz);
        String time = holder.df.format(new Date(millis));
        holder.textViewName.setText(surveyModelArrayList.get(position).getName());
        holder.textViewPoint.setText(surveyModelArrayList.get(position).getPoint() + " v");
        holder.textViewNum.setText(time + "");
        Glide.with(Contextor.getInstance().getContext()).load(surveyModelArrayList.get(position).getImage())
                .into(holder.imageViewSurvey);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackClick.clickItemProduct(surveyModelArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveyModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewPoint)
        TextView textViewPoint;
        @BindView(R.id.textViewNum)
        TextView textViewNum;
        @BindView(R.id.imageViewSurvey)
        ImageView imageViewSurvey;

        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemProduct(ViewAdsModel viewAdsModel);
    }
}
