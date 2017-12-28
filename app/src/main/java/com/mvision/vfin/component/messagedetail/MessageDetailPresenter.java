package com.mvision.vfin.component.messagedetail;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.message.MessageFragment;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.firebase.Firestore.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageDetailPresenter extends Presenter<MessageDetailContract.View> implements MessageDetailContract.Presenter {

    private MessageDetailContract.View view ;
    private MessageData messageData ;
    private int position ;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        messageData =  Parcels.unwrap(extras.getParcelable(MessageFragment.clickMessageData));
        position =  extras.getInt("position");
    }

    public MessageDetailPresenter(MessageDetailContract.View view){
        this.view = view;
    }

    @Override
    public void getMessageDetail() {
        view.setUpViewMessageDetail(messageData);
    }

    @Override
    public void getOnClickButton() {
        if (messageData.getOptions().btn.get(0).link.equals("vinpoint")){
            view.goToWalletHistory();
        }
    }

    @Override
    public void getDeleteMessage() {
        view.showLoading();
        MessageDetailManage.getInstance().deleteMessage(messageData.code, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.hideLoading();
                view.showDeleteMessageSuccess(position);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
