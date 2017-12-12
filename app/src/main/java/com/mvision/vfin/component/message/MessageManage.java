package com.mvision.vfin.component.message;

import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.firebase.Firestore.Query;
import java.util.ArrayList;

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

}
