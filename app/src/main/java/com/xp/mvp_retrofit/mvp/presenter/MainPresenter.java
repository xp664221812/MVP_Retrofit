package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.mvp.contract.RegisterContract;
import com.xp.mvp_retrofit.mvp.model.MainModel;
import com.xp.mvp_retrofit.mvp.model.RegisterModel;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    MainModel model;


    public MainPresenter() {
        model = new MainModel();
    }


}
