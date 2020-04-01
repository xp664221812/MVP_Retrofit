package com.xp.mvp_retrofit.mvp.model;

import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.ArticleResponseBody;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import java.util.List;

import io.reactivex.Observable;

public class HomeModel extends CommonModel implements HomeContract.Model {
//    @Override
//    public void requestBanner(HomeContract.IComicCallBack callBack) {
//        Api api = AppClient.initRetrofit().create(Api.class);
//        api.requestBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HttpResult<List<Banner>>>() {
//            @Override
//            public void accept(HttpResult<List<Banner>> list) throws Exception {
//                callBack.ok(list);
//            }
//        });
//    }

    @Override
    public Observable<HttpResult<List<Banner>>> requestBanner() {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.requestBanner();
    }

    @Override
    public Observable<HttpResult<List<Article>>> requestTopArticles() {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.requestTopArticles();
    }

    @Override
    public Observable<HttpResult<ArticleResponseBody>> requestArticles(int num) {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.requestArticles(num);
    }
}
