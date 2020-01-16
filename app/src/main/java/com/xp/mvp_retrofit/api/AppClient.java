package com.xp.mvp_retrofit.api;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;
    private static String API_URL = "https://api.github.com";

    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.d(message);
            }
        });
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mOkHttpClient = builder.addInterceptor(interceptor).callTimeout(10, TimeUnit.SECONDS).build();
    }


    public static Retrofit initRetrofit() {
        if (mRetrofit == null) {
            initOkHttpClient();
            mRetrofit = new Retrofit.Builder().baseUrl(API_URL).client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }


}
