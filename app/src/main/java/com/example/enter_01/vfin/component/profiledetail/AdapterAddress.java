package com.example.enter_01.vfin.component.profiledetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.profile.model.Address;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class AdapterAddress extends RecyclerView.Adapter<AdapterAddress.ViewHolder>{

    private ArrayList<Address>addresses ;

    public AdapterAddress(ArrayList<Address>addresses){
        this.addresses = addresses ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_address, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewAddressNumber.setText("");
        holder.textViewAddressDetail.setText(addresses.get(position).getProvince());

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewAddressNumber) TextView textViewAddressNumber;
        @BindView(R.id.textViewAddressDetail) TextView textViewAddressDetail;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
