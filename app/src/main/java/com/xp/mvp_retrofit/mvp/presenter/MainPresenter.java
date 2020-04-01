package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.mvp.contract.RegisterContract;
import com.xp.mvp_retrofit.mvp.model.MainModel;
import com.xp.mvp_retrofit.mvp.model.RegisterModel;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.UserInfoBody;
import com.xp.mvp_retrofit.widget.DefaultObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> implements MainContract.Presenter {


    @Override
    public MainContract.Model createModel() {
        return new MainModel();
    }


    @Override
    public void getUserInfo() {
        mModel.getUserInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DefaultObserver<HttpResult<UserInfoBody>>(rootView) {
            @Override
            public void onSuccess(HttpResult<UserInfoBody> result) {
                rootView.showUserInfo(result);
            }
        });
    }
}
