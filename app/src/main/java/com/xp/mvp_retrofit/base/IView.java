package com.xp.mvp_retrofit.base;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

public interface IView {
    //显示加载
    void showLoading();

    //隐藏加载
    void hideLoading();

    //显示错误信息
    void showError(String error);

//    void handleError(Exception e);

    default void showToast(String toast) {
        ToastUtils.showShort(toast);
    }


}
