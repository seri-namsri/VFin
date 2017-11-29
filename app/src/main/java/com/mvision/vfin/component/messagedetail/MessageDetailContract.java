package com.mvision.vfin.component.messagedetail;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.message.pojo.MessageData;

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
