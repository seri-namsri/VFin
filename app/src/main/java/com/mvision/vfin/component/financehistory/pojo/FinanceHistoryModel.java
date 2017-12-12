package com.mvision.vfin.component.financehistory.pojo;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.utility.ConvertDate;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryModel {
    public String getChannel() {
        if (channel.equals("buy")) {
            return "ซื้อ";
        } else if (channel.equals("sell")) {
            return "ขาย";
        } else if (channel.equals("fee")) {
            return "ฟรีค่าธรรมเนียม";
        }
        return channel;
    }

    @SerializedName("channel")
    public String channel;
    @SerializedName("note")
    public String note;

    public String getAmount() {
        int i = amount.indexOf("+");
        if (i == -1)
            amount = "<font color=#F37D7D>"+amount+"</font>";
        else
            amount = "<font color=#007F32>"+amount+"</font>";
        return amount;
    }

    @SerializedName("amount")
    public String amount;

    public String getTransactionDate() {
        return ConvertDate.getInstance()
                .TimestampToFormatDateAndTimeTH(transactionDate/1000+"", "วันที่ dd MMM yyyy HH:mm" +
                        " น.");
    }

    @SerializedName("transactionDate")
    public long transactionDate;
}
