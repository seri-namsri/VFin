package com.mvision.vfin.component.provinceamphur;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public class ProvinceAmphurAdapter extends RecyclerView.Adapter<ProvinceAmphurAdapter.ViewHolder> {
    private ProvinceResponseModel provinceResponseModel;
    private AmphurResponseModel amphurResponseModel;
    private OnClick onClick;

    public ProvinceAmphurAdapter(ProvinceResponseModel provinceResponseModel, OnClick onClick) {
        this.provinceResponseModel = provinceResponseModel;
        this.onClick = onClick;
    }

    public ProvinceAmphurAdapter(AmphurResponseModel amphurResponseModel, OnClick onClick) {
        this.amphurResponseModel = amphurResponseModel;
        this.onClick = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_list,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            holder.textViewName.setText(provinceResponseModel.result.get(position).provinceName);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.proVince(provinceResponseModel.result.get(position));
                }
            });
        }catch (NullPointerException e){
            holder.textViewName.setText(amphurResponseModel.result.get(position).amphurName);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.amphur(amphurResponseModel.result.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        try {
            return provinceResponseModel.result.size();
        }catch (NullPointerException e){
            return amphurResponseModel.result.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClick{
        void proVince(Province province);
        void amphur(Amphur amphur);
    }

}
