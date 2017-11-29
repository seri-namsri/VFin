package com.mvision.vfin.component.message;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.message.pojo.MessageData;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public interface MessageContract {

    interface View extends BaseView {
        void setUpViewMessage(ArrayList<MessageData>message);

    }

    interface Presenter {

        void getMessage();

    }
}
