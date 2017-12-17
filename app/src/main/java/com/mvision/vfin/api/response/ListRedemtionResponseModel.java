package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.reward.pojo.RewardModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class ListRedemtionResponseModel extends BaseRespone {
    @SerializedName("result")
    public ArrayList<RewardModel> result;




}
