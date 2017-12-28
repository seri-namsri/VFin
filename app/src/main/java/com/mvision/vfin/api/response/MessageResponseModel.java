package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.message.pojo.MessageData;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MessageResponseModel extends BaseRespone {
    @SerializedName("result")
    public  ArrayList<MessageData> result ;
}
