package com.mvision.vfin.api.request;

import com.mvision.vfin.api.modelrequest.TradeBuy;
import com.mvision.vfin.api.response.MyProductResponseModel;
import com.mvision.vfin.api.response.RewardDetailResponseModel;
import com.mvision.vfin.api.response.TradeBuyResponseModel;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Product {

    @POST
    Observable<TradeBuyResponseModel> tradeBuy(@Url String url, @Body TradeBuy body);

    @GET
    Observable<MyProductResponseModel> getMyProduct(@Url String url);

    @GET
    Observable<RewardDetailResponseModel> getRewardDetail(@Url String url);

}