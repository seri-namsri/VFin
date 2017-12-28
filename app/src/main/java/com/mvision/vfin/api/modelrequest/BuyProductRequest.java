package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.PreferencesMange;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class BuyProductRequest {
    @SerializedName("address")
    private AddressModel.MyAddress address;
    @SerializedName("productOnShelfCode")
    public String productOnShelfCode;
    @SerializedName("memberCode")
    public String memberCode = PreferencesMange.getInstance().getMemberID();
    @SerializedName("shippingPrice")
    public int shippingPrice;
    @SerializedName("systemFeePrice")
    public int systemFeePrice;
    @SerializedName("price")
    public int price;
    public BuyProductRequest(AddressModel.MyAddress address, String productOnShelfCode, int shippingPrice,
                             int systemFeePrice,int price){
        this.address = address;
        this.productOnShelfCode = productOnShelfCode;
        this.price = price;
        this.shippingPrice = shippingPrice;
        this.systemFeePrice = systemFeePrice;

    }


}
