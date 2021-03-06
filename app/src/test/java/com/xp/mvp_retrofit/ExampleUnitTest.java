package com.xp.mvp_retrofit;



import com.blankj.utilcode.util.LogUtils;
import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.Contributor;

import org.junit.Test;


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(message);
//                LogUtils.d(message);
            }
        });

        String API_URL = "https://api.github.com";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        Api api = retrofit.create(Api.class);


        api.contributors("jetbrains", "Kotlin").enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> list = response.body();
                System.out.println(list.size());
            }


            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });


    }
}