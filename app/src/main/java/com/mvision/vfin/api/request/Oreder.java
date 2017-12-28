package com.mvision.vfin.api.request;


import com.mvision.vfin.api.modelrequest.BuyProductRequest;
import com.mvision.vfin.api.modelrequest.CreateOrderRequest;
import com.mvision.vfin.api.modelrequest.OrderCallFeeRequest;
import com.mvision.vfin.api.response.CreateOrderResponseModel;
import com.mvision.vfin.api.response.OrderCalFeeResponseModel;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.myaddress.MyAddressModel;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Oreder {

    @POST
    Observable<OrderCalFeeResponseModel> getOrderCalFee(@Url String url, @Body
            AddressModel.MyAddress addressModel);

    @POST
    Observable<CreateOrderResponseModel> createOrder(@Url String url,@Body CreateOrderRequest
                                                                    createOrderRequest);
    @POST
    Observable<CreateOrderResponseModel> buyPruduct(@Url String url,@Body BuyProductRequest
                                                                    buyProductRequest);

}