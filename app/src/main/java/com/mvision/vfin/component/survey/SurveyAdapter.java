package com.mvision.vfin.component.survey;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.component.survey.pojo.SurveyModel;
import com.mvision.vfin.utility.Contextor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private ArrayList<SurveyModel> surveyModelArrayList;
    private CallBackClick callBackClick ;
    public SurveyAdapter(ArrayList<SurveyModel> surveyModelArrayList,CallBackClick callBackClick) {
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



        holder.textViewName.setText(surveyModelArrayList.get(position).getName());
        holder.textViewPoint.setText(surveyModelArrayList.get(position).getPoint() + " v");
        holder.textViewNum.setText(surveyModelArrayList.get(position).getNum() + " ข้อ");
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

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemProduct(SurveyModel surveyModel);
    }
}
