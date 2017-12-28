package com.mvision.vfin.component.message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.utility.ConvertDate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  ArrayList<MessageData> messageModel ;
    private CallBackClick callBackClick ;
    public  MessageAdapter(ArrayList<MessageData> messageModel,CallBackClick callBackClick){
        this.messageModel = messageModel;
        this.callBackClick = callBackClick;
    }

    public void addMessage(ArrayList<MessageData> messageModel) {
        checkLoading();
        this.messageModel.addAll(messageModel);
        notifyItemRangeInserted(this.messageModel.size() + 1, messageModel.size());
    }

    public void checkLoading(){
        if (this.messageModel.get(this
                .messageModel.size()-1) == null){
            this.messageModel.remove(this
                    .messageModel.size()-1);
            notifyItemRemoved(this.messageModel.size());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case  1 :
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message, parent, false));
            case 2:
                return new ViewHolderLoading(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_load_more, parent, false));
        }

      return null;
    }

    @Override
    public int getItemViewType(int position) {
        return (messageModel.get(position)!=null) ? 1 : 2;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            ViewHolder viewHolderMessage = (ViewHolder) holder;
            viewHolderMessage.textViewMessageTitle.setText(messageModel.get(position).getTitle());
            viewHolderMessage.textViewMessageCreateDate.setText("เวลาส่ง : " +ConvertDate.getInstance()
                    .TimestampToFormatDateAndTimeTH(messageModel
                            .get(position)
                            .getCreatedDate()+"","dd MMM yyyy HH:mm น."));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackClick.clickItemMessage(messageModel.get(position),position);
                }
            });
        }catch (ClassCastException e){}

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
        void clickItemMessage(MessageData messageData,int position);
    }


    public class ViewHolderLoading extends RecyclerView.ViewHolder {

        public ViewHolderLoading(View itemView) {
            super(itemView);
        }
    }
}
