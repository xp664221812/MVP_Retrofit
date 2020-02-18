package com.xp.mvp_retrofit.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment implements IView {
    protected P mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }

    }


    protected abstract P createPresenter();
}
