package com.example.enter_01.vfin.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.enter_01.vfin.BuildConfig;
import com.example.enter_01.vfin.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public Retrofit getRetrofitGateWay() {

        return retrofitNewApiGateway();
    }

    public Retrofit getRetrofit(String api) {
        return retrofit(api);
    }


    private Retrofit retrofitApi;

    protected Retrofit retrofit() {
        try {
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://52.74.191.27:8080")
                        .client(getRequestHeader())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                return retrofit;
        } catch (Exception e) {
            return retrofit;
        }

    }

    protected Retrofit retrofitNewApiGateway() {
        try {
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl("")
                        .client(getRequestHeader())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                return retrofit;
        } catch (Exception e) {
            return retrofit;
        }

    }

    public Retrofit retrofitWithBaseUrl(String api) {
        Retrofit retrofit = null;
        try {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(api)
                    .client(getRequestHeader())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit;

        } catch (Exception e) {
            return retrofit;
        }

    }

    protected Retrofit retrofit(String api) {
        // String url =   new Apilist().stringGetBaseUrl();
        if (retrofitApi == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            retrofitApi = new Retrofit.Builder()
                    .baseUrl(api)
                    .client(getRequestHeader(api))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();
            return retrofitApi;
        } else {
            return retrofitApi;
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


    private Cache provideCache() {
        Cache cache = null;
        try {

            cache = new Cache(new File(Contextor.getInstance().getContext().getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
            Log.e("Cache", "ok" + cache.directory());
        } catch (Exception e) {
            Log.e("Cache", "Could not create Cache!");
        }
        return cache;
    }


    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) Contextor.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


 /*   private OkHttpClient getRequestHeader() {

        OkHttpClient client = new OkHttpClient
                .Builder()
                .readTimeout(1000000, TimeUnit.SECONDS)
                .connectTimeout(1000000, TimeUnit.SECONDS)
                .addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();

        return client;
    }*/


    private OkHttpClient getRequestHeader(String api) {
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
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "key=" +
                                "AAAAW6_DU08:APA91bEtbrFpYKU4PSHuF2YiPq-TYe1764gw25HVU2KbZsOTof--OabR6pQIBTjoM8gMjec3W3MRYGH2LZ-w1MOpQ9mS7WCOwAUoGrII_ne8Nn9xsS7zzVttVO3Q-GE066f4U_RPBV1S").header("Content-Type", "application/json"); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return okHttpClient.build();
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
                        .header(Contextor.getInstance().getContext().getString(R.string.header_deviceId),
                                Utility.getDeviceID(Contextor.getInstance().getContext
                                        ()))
                        .header(Contextor.getInstance().getContext().getString(R.string
                                .header_api), "2")
                        .header(Contextor.getInstance().getContext().getString(R.string.header_package), packageName)
                     //   .header(Contextor.getInstance().getContext().getString(R.string
                      //  .header_signature),Utilities.Decryptor.byteArrayToHex(baseKey.getBytes()))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_timestamp), "" + Calendar.getInstance().getTimeInMillis())
                       // .header(Contextor.getInstance().getContext().getString(R.string
                       // .header_token), SharedPrefs.getInstance(context).getTokenSession())
                      //  .header(Contextor.getInstance().getContext().getString(R.string
                      //  .header_member_object_id), SharedPrefs.getInstance(context)
                      //  .getMemberObjectId())
                    //    .header(Contextor.getInstance().getContext().getString(R.string
                     //   .header_language), locale)
//                        .header(Contextor.getInstance().getContext().getString(R.string
                      //          .header_imei), Utility.Device.getIMEI1(Contextor.getInstance()
                     //   .getContext()))
                      //  .header(Contextor.getInstance().getContext().getString(R.string
                       // .header_imei2), Utility.Device.getIMEI2(Contextor.getInstance()
                      //  .getContext()))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_model), Utility.Device.getDeviceName())
                        .header(Contextor.getInstance().getContext().getString(R.string.header_version), String.valueOf(BuildConfig.VERSION_CODE))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_mac_address), UtilsNetwork.getMACAddress("wlan0"))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_operator), UtilsNetwork.getSimOperatorName(Contextor.getInstance().getContext()))
                        .header(Contextor.getInstance().getContext().getString(R.string.header_operator_code), Utility.TelephonyManagers.getInstance(Contextor.getInstance().getContext()).getSimOperator()); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return okHttpClient.build();
    }


}
