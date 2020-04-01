package com.xp.mvp_retrofit.base;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V> {

    protected V rootView;

    protected M mModel;

    public BasePresenter() {
        onStart();
    }

    public abstract M createModel();

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
        mModel = createModel();
    }

    @Override
    public void onDestroy() {
        rootView = null;
    }
}
