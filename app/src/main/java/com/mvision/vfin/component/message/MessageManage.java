package com.mvision.vfin.component.message;

import com.mvision.vfin.api.request.Message;
import com.mvision.vfin.api.response.MessageResponseModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageManage {

    private static MessageManage instance = null;

    public static MessageManage getInstance() {
        if (instance == null)
            instance = new MessageManage();
        return instance;
    }

    public void getMessageList(final String type, final int skip, final int limit, final Query.CallBackData callBackData){
        RetrofitUtility.getInstance().getRetrofit().create(Message.class).getMessageList(GetKey.getInstance()
                        .apiMessageList(GetKey.getInstance().getSignatures())+type, PreferencesMange
                        .getInstance().getMemberID(), skip, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getMessageList(type, skip, limit,callBackData);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(MessageResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }

}
