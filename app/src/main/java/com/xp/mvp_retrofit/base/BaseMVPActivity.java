package com.xp.mvp_retrofit.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xp.mvp_retrofit.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMVPActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    private Toolbar mToolbar;
    public P mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }

        initFromIntent(getIntent());
        mUnbinder = ButterKnife.bind(this);
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            LinearLayout back = mToolbar.findViewById(R.id.ll_back);

            if (back != null) {
                back.setVisibility(View.VISIBLE);
                back.setOnClickListener(v -> onToolbarBackPressed());
            }
        }

        initData(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    protected abstract P createPresenter();

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    protected abstract void initFromIntent(Intent intent);

    protected abstract int initLayout();

    protected void onToolbarBackPressed() {

    }

}
