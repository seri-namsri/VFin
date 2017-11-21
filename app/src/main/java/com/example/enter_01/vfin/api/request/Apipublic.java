package com.example.enter_01.vfin.api.request;

import com.example.enter_01.vfin.api.modelrequest.RequestFcm;
import com.example.enter_01.vfin.api.response.ModelFcm;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Apipublic {


    @POST("fcm/send")
    Observable<ModelFcm> sendFcm(@Body RequestFcm action);

}