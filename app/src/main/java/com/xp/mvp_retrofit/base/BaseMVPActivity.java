package com.xp.mvp_retrofit.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.mvp.IPresenter;
import com.xp.mvp_retrofit.mvp.IView;

import butterknife.ButterKnife;

public abstract class BaseMVPActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    private Toolbar mToolbar;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }

        initFromIntent(getIntent());
        ButterKnife.bind(this);
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        initData(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    protected abstract P createPresenter();

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    protected abstract void initFromIntent(Intent intent);

    protected abstract int initLayout();
}
