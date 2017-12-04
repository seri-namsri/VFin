package com.mvision.vfin.customview.dialoglist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder>{
    ArrayList<Object>arrayList ;
    public DialogListAdapter(ArrayList<Object>arrayList,Class t){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_list,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return R.layout.adapter_dialog_list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
