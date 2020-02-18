package com.xp.mvp_retrofit.mvp.model;

import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.UserInfoBody;

import io.reactivex.Observable;

public class MainModel implements MainContract.Model {
    @Override
    public Observable<HttpResult<UserInfoBody>> getUserInfo() {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.getUserInfo();
    }
}
