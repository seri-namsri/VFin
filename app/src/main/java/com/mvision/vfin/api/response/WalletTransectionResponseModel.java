package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/12/2017 AD.
 */

public class WalletTransectionResponseModel {
    @SerializedName("result")
    public ArrayList<FinanceHistoryModel>result;
}
