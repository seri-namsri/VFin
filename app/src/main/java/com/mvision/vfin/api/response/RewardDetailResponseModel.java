package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.myproduct.tradingclose.pojo.MyproductModel;
import com.mvision.vfin.component.reward.pojo.RewardModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class RewardDetailResponseModel extends BaseRespone{
    @SerializedName("result")
    public RewardModel result;

}
