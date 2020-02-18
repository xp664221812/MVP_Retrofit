package com.xp.mvp_retrofit.api;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.xp.mvp_retrofit.http.MyCookieJarImpl;
import com.xp.mvp_retrofit.http.interceptor.CacheInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;
    private static String API_URL = "https://www.wanandroid.com";

    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> LogUtils.d(message));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mOkHttpClient = builder
                .cookieJar(new MyCookieJarImpl())
//                .addInterceptor(new CacheInterceptor())
                .addInterceptor(interceptor)
                .callTimeout(10, TimeUnit.SECONDS)
                .build();
    }


    public static Retrofit initRetrofit() {
        if (mRetrofit == null) {
            initOkHttpClient();
            mRetrofit = new Retrofit.Builder().baseUrl(API_URL).client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }


}
