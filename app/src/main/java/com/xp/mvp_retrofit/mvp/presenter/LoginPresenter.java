package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.LoginContract;
import com.xp.mvp_retrofit.mvp.model.LoginModel;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.LoginData;
import com.xp.mvp_retrofit.widget.DefaultObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> implements LoginContract.Presenter {



    @Override
    public LoginContract.Model createModel() {
        return new LoginModel();
    }

    @Override
    public void login(String username, String password) {
        mModel.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<HttpResult<LoginData>>(rootView) {
            @Override
            public void onSuccess(HttpResult<LoginData> result) {
                rootView.loginSuccess(result);

            }
        });


    }
}
