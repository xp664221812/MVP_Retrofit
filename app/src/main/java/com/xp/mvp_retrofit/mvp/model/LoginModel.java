package com.xp.mvp_retrofit.mvp.model;

import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.LoginContract;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.LoginData;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.Model {


    @Override
    public Observable<HttpResult<LoginData>> login(String username, String password) {
        Api api = AppClient.initRetrofit().create(Api.class);
        return api.login(username, password);
    }
}
