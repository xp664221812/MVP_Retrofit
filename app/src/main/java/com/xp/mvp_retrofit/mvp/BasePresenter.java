package com.xp.mvp_retrofit.mvp;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    private V rootView;

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public V get() {
        return rootView;
    }

    @Override
    public void attach(V view) {
        rootView = view;
    }

    @Override
    public void onDestroy() {
        rootView = null;
    }
}
