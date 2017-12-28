package com.mvision.vfin.component.message;

import android.content.Intent;

import com.mvision.vfin.api.response.MessageResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MessagePresenter extends Presenter<MessageContract.View> implements MessageContract.Presenter {

    private MessageContract.View view;
    private int skip = 0;
    private int limit = 10;

    public MessagePresenter(MessageContract.View view) {
        this.view = view;
    }

    @Override
    public void getMessage() {
        view.showLoading();
        MessageManage.getInstance().getMessageList("single", skip, limit, callBackData);


    }

    @Override
    public void getMessageMore() {
        MessageManage.getInstance().getMessageList("single", skip*limit, limit,
                callBackData);
    }

    @Override
    public void getRemoveMessage(int requestCode, Intent data) {
        if (requestCode == 1){
            int position = data.getIntExtra("position",0);
            view.getRemoveMessageSuccess(position);
        }
    }

    private Query.CallBackData callBackData = new Query.CallBackData() {
        @Override
        public <T> void onSuccess(T t) {
            MessageResponseModel messageResponseModel = (MessageResponseModel) t;
            if (messageResponseModel.result != null && messageResponseModel.result.size() > 0) {

                if (messageResponseModel.result.size() >= limit-1)
                    messageResponseModel.result.add(null);

                if (skip == 0) {
                    view.hideLoading();
                    view.setUpViewMessage(messageResponseModel);
                } else {
                    view.setUpViewMessageMore(messageResponseModel);
                }
                skip++;
            } else if (messageResponseModel.result != null && messageResponseModel.result.size() == 0) {
                view.hideLoadingMore();
                if (skip == 0) {
                    view.showTextNotFound();
                }
            }

        }

        @Override
        public <T> void onSuccessAll(ArrayList<T> tArrayList) {

        }

        @Override
        public void onFail(String error) {

        }
    };
}
