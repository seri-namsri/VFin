package com.mvision.vfin.api.request;

import com.mvision.vfin.api.response.ListRedemtionResponseModel;
import com.mvision.vfin.api.response.WalletTransectionResponseModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Market {

    @GET("marketservice/v1/trade/listRedemption")
    Observable<ListRedemtionResponseModel> getListRedemption();

}