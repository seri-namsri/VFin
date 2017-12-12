package com.mvision.vfin.api.request;

import com.mvision.vfin.api.response.WalletTransectionResponseModel;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Wallet {

    @GET("walletservice/v1/wallet/getWalletTransaction")
    Observable<WalletTransectionResponseModel> getWalletTransaction(@Query("memberCode")String memberCode,
                                                                    @Query("pageSize")int pageSize, @Query
                                                                   ("startPosition")int
                                                                   startPosition);

}