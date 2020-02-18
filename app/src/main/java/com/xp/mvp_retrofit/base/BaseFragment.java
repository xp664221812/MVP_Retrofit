package com.xp.mvp_retrofit.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private boolean isViewPrepared = false;

    private boolean hasLoaded = false;

    protected Context mContext;
    private View mRootView;

    private Unbinder mUnbinder;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResId(), null);


        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }

        isViewPrepared = true;

        initView();

        lazyLoadIfPrepared();

    }


    private void lazyLoadIfPrepared() {
        if (getUserVisibleHint() && isViewPrepared && !hasLoaded) {
            lazyLoad();
            hasLoaded = true;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            lazyLoadIfPrepared();
        }
    }

    protected abstract void initView();

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void lazyLoad();

    public boolean useEventBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mContext = null;
        mRootView = null;
    }


    //    /**
//     * 无网状态—>有网状态 的自动重连操作，子类可重写该方法
//     */
//    public void doReConnected() {
//        lazyLoad();
//    }

}
