package com.example.enter_01.vfin.component.message;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.message.pojo.MessageData;
import com.example.enter_01.vfin.component.message.pojo.MessageModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;

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
        MessageManage.getInstance().getMessage(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                view.hideLoading();
                view.setUpViewMessage((ArrayList<MessageData>) tArrayList);
            }

            @Override
            public void onFail(String error) {
                Log.d("getMessage",error);
            }
        });
    }
}
