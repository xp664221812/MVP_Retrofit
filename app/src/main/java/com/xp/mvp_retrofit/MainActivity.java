package com.xp.mvp_retrofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.api.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    //https://api.github.com/repos/jetbrains/Kotlin/contributors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Api api = AppClient.initRetrofit().create(Api.class);
//
//        api.contributors("jetbrains", "Kotlin").enqueue(new Callback<List<Contributor>>() {
//            @Override
//            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                List<Contributor> list = response.body();
//                LogUtils.d(list.size());
//            }
//
//
//            @Override
//            public void onFailure(Call<List<Contributor>> call, Throwable t) {
//                LogUtils.d(t.getCause());
//            }
//        });


    }
}
