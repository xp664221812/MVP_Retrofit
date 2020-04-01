package com.xp.mvp_retrofit.mvp.model;

import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.CommonContract;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import io.reactivex.Observable;

public class CommonModel implements CommonContract.Model {

    @Override
    public Observable<HttpResult<Object>> addCollectArticle(int id) {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.addCollectArticle(id);
    }

    @Override
    public Observable<HttpResult<Object>> cancelCollectArticle(int id) {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.cancelCollectArticle(id);
    }
}
