package com.mvision.vfin.api.request;

import com.mvision.vfin.api.response.MessageResponseModel;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Message {

    @GET
    Observable<MessageResponseModel> getMessageList(@Url String url, @Query("memberCode")String
            memberCode, @Query("skip")int skip, @Query("limit")int limit);

    @POST
    Observable<Object> deleteMessage(@Url String url);

}