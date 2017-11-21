package com.example.enter_01.vfin.component.message;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.message.pojo.MessageData;
import com.example.enter_01.vfin.component.message.pojo.MessageModel;

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
