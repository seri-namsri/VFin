package com.mvision.vfin.component.myaddress;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyAddressResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder>{

    private MyAddressResponseModel myAddressResponseModel ;
    public MyAddressAdapter(MyAddressResponseModel myAddressResponseModel){
        this.myAddressResponseModel = myAddressResponseModel;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_my_address, parent, false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textViewAddress.setText(myAddressResponseModel.result.get(position).houseNo+ "" +
                    " "+ myAddressResponseModel.result.get(position).alley + " "+
                    myAddressResponseModel.result.get(position).road+ " "+
                    myAddressResponseModel.result.get(position).subDistrict  + " "+
                    myAddressResponseModel.result.get(position).district + " "+
                    myAddressResponseModel.result.get(position).province + " "+
                    myAddressResponseModel.result.get(position).postalCode   );
    }

    @Override
    public int getItemCount() {
        return myAddressResponseModel.result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewAddress)
        TextView  textViewAddress;
        @BindView(R.id.buttonEdit)
        Button buttonEdit;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickItemMore{
        void itemClick(int position);
    }

}
