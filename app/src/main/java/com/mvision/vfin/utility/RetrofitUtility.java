package com.mvision.vfin.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mvision.vfin.BuildConfig;
import com.mvision.vfin.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvision.vfin.component.configkey.GetKey;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by Hytexts_Android on 20/3/2560.
 */
public class RetrofitUtility {

    private static RetrofitUtility instance = null;

    public static RetrofitUtility getInstance() {
        if (instance == null)
            instance = new RetrofitUtility();

        return instance;
    }


    private Retrofit retrofit;

    public Retrofit getRetrofit() {

        return retrofit();
    }




    private Retrofit retrofitApi;

    protected Retrofit retrofit() {
        try {
                 Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(GetKey.getInstance().getBaseApi(GetKey.getInstance()
                               .getSignatures()))
                        .client(getRequestHeader())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                return retrofit;
        } catch (Exception e) {
            return retrofit;
        }

    }







    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("Logging ", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }


    public Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!checkIfHasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static final String CACHE_CONTROL = "Cache-Control";

    private Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }




    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) Contextor.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }





    private OkHttpClient getRequestHeader() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        okHttpClient.readTimeout(1000000, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(1000000, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(provideHttpLoggingInterceptor());
        okHttpClient.addInterceptor(provideOfflineCacheInterceptor());
        okHttpClient.addNetworkInterceptor(provideCacheInterceptor());
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                final String packageName = Contextor.getInstance().getContext().getPackageName();

                Request.Builder requestBuilder = original.newBuilder()
                        .header(Contextor.getInstance().getContext().getString(R.string.header_cache),
                                Contextor.getInstance().getContext().getString(R.string.value_cache))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_deviceId),Utility.getDeviceID(Contextor.getInstance().getContext()))
                        .header(Contextor.getInstance().getContext().getString(R.string
                                .header_api), "1")
                        .header(Contextor.getInstance().getContext().getString(R.string.header_package), packageName)
                        .header(Contextor.getInstance().getContext().getString(R.string.header_token_session),
                                PreferencesMange.getInstance().getTokenSession())
                     //   .header(Contextor.getInstance().getContext().getString(R.string
                      //  .header_signature),Utilities.Decryptor.byteArrayToHex(baseKey.getBytes()))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_timestamp), "" + Calendar.getInstance().getTimeInMillis())
                        .header(Contextor.getInstance().getContext().getString(R.string
                        .header_language), PreferencesMange.getInstance().getLanguage())
                        .header(Contextor.getInstance().getContext().getString(R.string
                                .header_imei), Utility.getIMEI1(Contextor.getInstance().getContext())+"")
                        .header(Contextor.getInstance().getContext().getString(R.string
                        .header_imei2),  Utility.getIMEI2(Contextor.getInstance().getContext())+"")
                        .header(Contextor.getInstance().getContext().getString(R.string.header_model),Utility.getDeviceName())
                        .header(Contextor.getInstance().getContext().getString(R.string.header_version), String.valueOf(BuildConfig.VERSION_CODE))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_mac_address),  Utility.getMACAddress("wlan0"))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_operator), UtilsNetwork.getInstance().getSimOperatorName(Contextor.getInstance().getContext()))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_operator_code), Utility.TelephonyManagers.getInstance(Contextor.getInstance().getContext()).getSimOperator()); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return okHttpClient.build();
    }


}
