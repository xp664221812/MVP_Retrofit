package com.xp.mvp_retrofit.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
//
//    @GET("/repos/{owner}/{repo}/contributors")
//    Call<List<Contributor>> register(
//            @Path("owner") String owner,
//            @Path("repo") String repo);

    @FormUrlEncoded
    @POST("/user/register")
    Observable register(@Field("username")String username,@Field("password")String password
            ,@Field("repassword")String repassword);


}
