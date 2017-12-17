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
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Product {

    @POST("marketservice/v1/trade/buy")
    Observable<TradeBuyResponseModel> tradeBuy(@Body TradeBuy body);

    @GET("marketservice/v1/trade/findMyItem/{status}/{memberCode}")
    Observable<MyProductResponseModel> getMyProduct(@Path("status")String itemStatus
            , @Path("memberCode")String memberCode);

    @GET("marketservice/v1/trade/product/{id}")
    Observable<RewardDetailResponseModel> getRewardDetail(@Path("id") String string);

}