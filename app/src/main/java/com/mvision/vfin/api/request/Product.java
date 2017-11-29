package com.mvision.vfin.api.request;

import com.mvision.vfin.api.modelrequest.TradeBuy;
import com.mvision.vfin.api.response.TradeBuyResponseModel;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Product {

    @POST("marketservice/v1/trade/buy")
    Observable<TradeBuyResponseModel> tradebuy(@Body TradeBuy body);

}