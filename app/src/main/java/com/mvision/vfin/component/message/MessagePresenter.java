package com.mvision.vfin.component.message;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MessagePresenter extends Presenter<MessageContract.View> implements MessageContract.Presenter{

    private MessageContract.View view ;
    public MessagePresenter (MessageContract.View view){
        this.view = view;
    }

    @Override
    public void getMessage() {
        view.showLoading();

    }
}
