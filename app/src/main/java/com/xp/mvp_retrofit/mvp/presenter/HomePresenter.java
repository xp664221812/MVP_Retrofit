package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.mvp.model.HomeModel;
import com.xp.mvp_retrofit.mvp.model.MainModel;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    HomeModel model;


    public HomePresenter() {
        model = new HomeModel();
    }


    @Override
    public void requestBanner() {
        model.requestBanner(new HomeContract.IComicCallBack() {
            @Override
            public void ok(HttpResult<List<Banner>> result) {
                rootView.showBanner(result.data);
            }
        });
    }
}
