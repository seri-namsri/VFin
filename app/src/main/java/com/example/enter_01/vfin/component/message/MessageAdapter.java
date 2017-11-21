package com.example.enter_01.vfin.component.message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.message.pojo.MessageData;
import com.example.enter_01.vfin.component.message.pojo.MessageModel;
import com.example.enter_01.vfin.utility.ConvertDate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private  ArrayList<MessageData> messageModel ;
    private CallBackClick callBackClick ;
    public  MessageAdapter(ArrayList<MessageData> messageModel,CallBackClick callBackClick){
        this.messageModel = messageModel;
        this.callBackClick = callBackClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewMessageTitle.setText(messageModel.get(position).getTitle());
        holder.textViewMessageCreateDate.setText("เวลาส่ง : " +ConvertDate.getInstance()
                .TimestampToFormatDateAndTimeTH(messageModel
                .get(position)
                .getCreate_date()+"","dd MMM yyyy HH:mm น."));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackClick.clickItemMessage(messageModel.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewMessageTitle)
        TextView textViewMessageTitle;
        @BindView(R.id.textViewMessageCreateDate) TextView textViewMessageCreateDate;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick{
        void clickItemMessage(MessageData messageData);
    }
}
