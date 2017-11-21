package com.example.enter_01.vfin.component.messagedetail;

import android.os.Bundle;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.message.MessageFragment;
import com.example.enter_01.vfin.component.message.pojo.MessageData;

import org.parceler.Parcels;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageDetailPresenter extends Presenter<MessageDetailContract.View> implements MessageDetailContract.Presenter {

    private MessageDetailContract.View view ;
    private MessageData messageData ;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        messageData =  Parcels.unwrap(extras.getParcelable(MessageFragment.clickMessageData));
    }

    public MessageDetailPresenter(MessageDetailContract.View view){
        this.view = view;
    }

    @Override
    public void getMessageDetail() {
        view.setUpViewMessageDetail(messageData);
    }
}
