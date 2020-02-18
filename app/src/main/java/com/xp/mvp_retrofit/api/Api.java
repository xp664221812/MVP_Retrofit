package com.xp.mvp_retrofit.api;

import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.ArticleResponseBody;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.LoginData;
import com.xp.mvp_retrofit.storage.beans.UserInfoBody;

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
    Observable<HttpResult> register(@Field("username") String username, @Field("password") String password
            , @Field("repassword") String repassword);


    @GET("/banner/json")
    Observable<HttpResult<List<Banner>>> requestBanner();

    @GET("/article/top/json")
    Observable<HttpResult<List<Article>>> requestTopArticles();

    @GET("/article/list/{num}/json")
    Observable<HttpResult<ArticleResponseBody>> requestArticles(@Path("num") int num);


    @FormUrlEncoded
    @POST("/user/login")
    Observable<HttpResult<LoginData>> login(@Field("username") String username, @Field("password") String password);



    @GET("/lg/coin/userinfo/json")
    Observable<HttpResult<UserInfoBody>> getUserInfo();
}
