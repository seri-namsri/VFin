package com.mvision.vfin.component.myaddress;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {

    private MyAddressResponseModel myAddressResponseModel;
    private OnClickItem onClickItem;

    public MyAddressAdapter(MyAddressResponseModel myAddressResponseModel, OnClickItem onClickItem) {
        this.myAddressResponseModel = myAddressResponseModel;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_my_address, parent, false));

    }

    private int clickPosition = -1;

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    public void setAddAddress() {
        clickPosition = -1;
        notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            if (clickPosition >= 0 && clickPosition == position) {
                holder.imageClick.setBackgroundResource(R.drawable.checked);
            } else if (clickPosition < 0 && myAddressResponseModel.result.get
                    (position).isPrimary.equals("yes")) {
                holder.imageClick.setBackgroundResource(R.drawable.checked);
            } else {
                holder.imageClick.setBackgroundResource(R.drawable.button_background_black_corner);
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        holder.textViewAddress.setText(myAddressResponseModel.result.get(position).houseNo + " " +
                myAddressResponseModel.result.get(position).details + " " +
                myAddressResponseModel.result.get(position).province.provinceName + " " +
                myAddressResponseModel.result.get(position).district + " " +
                myAddressResponseModel.result.get(position).postalCode);
        holder.textViewAddressName.setText(myAddressResponseModel.result.get(position).getName());
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.itemClickEdit(myAddressResponseModel.result.get(position), position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.itemClickSelect(myAddressResponseModel.result.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myAddressResponseModel.result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewAddress)
        TextView textViewAddress;
        @BindView(R.id.textViewAddressName)
        TextView textViewAddressName;
        @BindView(R.id.buttonEdit)
        Button buttonEdit;
        @BindView(R.id.imageClick)
        ImageView imageClick;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickItem {
        void itemClickEdit(AddressModel addressModel, int position);

        void itemClickSelect(AddressModel addressModel, int position);
    }

}
