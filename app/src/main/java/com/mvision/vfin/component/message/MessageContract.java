package com.mvision.vfin.component.message;

import android.content.Intent;

import com.mvision.vfin.api.response.MessageResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.message.pojo.MessageData;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface MessageContract {

    interface View extends BaseView {
        void setUpViewMessage(MessageResponseModel messageResponseModel);
        void setUpViewMessageMore(MessageResponseModel messageResponseModel);
        void showTextNotFound();
        void hideLoadingMore();
        void getRemoveMessageSuccess(int position);

    }

    interface Presenter {
        void getMessage();
        void getMessageMore();
        void getRemoveMessage(int requestCode, Intent data);

    }
}
