package com.xp.mvp_retrofit.base;

public interface IPresenter<V extends IView> {


    void onStart();

    void onDestroy();

    void attach(V view);

    V get();
}
