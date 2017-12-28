package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.PreferencesMange;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class OrderCallFeeRequest {
    @SerializedName("orderAddressRequest")
    private AddressModel orderAddressRequest;
    public OrderCallFeeRequest(AddressModel orderAddressRequest){
        this.orderAddressRequest = orderAddressRequest;
    }
}
