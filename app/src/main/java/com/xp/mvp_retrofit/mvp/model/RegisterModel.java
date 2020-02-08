package com.xp.mvp_retrofit.mvp.model;

import com.blankj.utilcode.util.LogUtils;
import com.xp.mvp_retrofit.api.Api;
import com.xp.mvp_retrofit.api.AppClient;
import com.xp.mvp_retrofit.mvp.contract.RegisterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements RegisterContract.Model {
    @Override
    public void register(String username, String password, String repassword, RegisterContract.IComicCallBack callBack) {

        Api api = AppClient.initRetrofit().create(Api.class);
        api.register(username, password, repassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtils.d(o);
            }
        });

    }
}
