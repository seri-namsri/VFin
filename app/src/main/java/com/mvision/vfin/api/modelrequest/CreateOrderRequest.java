package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.PreferencesMange;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class CreateOrderRequest {
    @SerializedName("address")
    private AddressModel.MyAddress address;
    @SerializedName("payFee")
    private PayFee payFee;
    public CreateOrderRequest(AddressModel.MyAddress address,String itemCode, int shippingPrice, int systemFeePrice){
        this.address = address;
        this.payFee = new PayFee(itemCode,shippingPrice,systemFeePrice);
    }

    public class PayFee{
        @SerializedName("itemCode")
        public String itemCode;
        @SerializedName("memberCode")
        public String memberCode;
        @SerializedName("shippingPrice")
        public int shippingPrice;

        public PayFee(String itemCode, int shippingPrice, int systemFeePrice) {
            this.itemCode = itemCode;
            this.memberCode = PreferencesMange.getInstance().getMemberID();
            this.shippingPrice = shippingPrice;
            this.systemFeePrice = systemFeePrice;
        }

        @SerializedName("systemFeePrice")
        public int systemFeePrice;


    }
}
