package com.xp.mvp_retrofit.mvp.model;

import com.blankj.utilcode.util.LogUtils;
import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {
    @Override
    public void requestBanner(HomeContract.IComicCallBack callBack) {
        Api api = AppClient.initRetrofit().create(Api.class);
        api.requestBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HttpResult<List<Banner>>>() {
            @Override
            public void accept(HttpResult<List<Banner>> list) throws Exception {
                callBack.ok(list);
            }
        });
    }
}
