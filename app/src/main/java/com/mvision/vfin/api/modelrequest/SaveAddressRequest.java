package com.mvision.vfin.api.modelrequest;

import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.PreferencesMange;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class SaveAddressRequest {
    private String memberCode;
    private AddressModel address;
    public SaveAddressRequest(AddressModel addressModel){
        this.memberCode = PreferencesMange.getInstance().getMemberID();
        this.address = address;
    }
}
