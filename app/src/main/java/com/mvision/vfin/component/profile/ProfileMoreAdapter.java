package com.mvision.vfin.component.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.component.profile.model.ProfileMore;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class ProfileMoreAdapter extends RecyclerView.Adapter<ProfileMoreAdapter.ViewHolder>{

    private ArrayList<ProfileMore>arrayList ;
    private  OnClickItemMore onClickItemMore ;
    public ProfileMoreAdapter(ArrayList<ProfileMore>arrayList,OnClickItemMore onClickItemMore){
        this.arrayList = arrayList;
        this.onClickItemMore = onClickItemMore;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile_more,
                parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
           holder.textViewName.setText(arrayList.get(position).getName());
           holder.imageViewItem.setImageResource(arrayList.get(position).getRes_image());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onClickItemMore.itemClick(position);
               }
           });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewItem)
        ImageView imageViewItem;
        @BindView(R.id.textViewName) TextView textViewName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickItemMore{
        void itemClick(int position);
    }

}
