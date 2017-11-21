package com.example.enter_01.vfin.component.messagedetail;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.message.pojo.MessageData;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public interface MessageDetailContract {

    interface View extends BaseView {

        void setUpViewMessageDetail(MessageData messageDetail);
    }

    interface Presenter {
        void getMessageDetail();

    }

}
