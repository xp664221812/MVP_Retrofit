package com.xp.mvp_retrofit.base;

public interface IView {
    //显示加载
    void showLoading();

    //隐藏加载
    void hideLoading();

    void handleError(Exception e);

}
