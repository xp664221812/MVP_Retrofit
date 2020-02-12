package com.xp.mvp_retrofit.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xp.mvp_retrofit.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initFromIntent(getIntent());
        ButterKnife.bind(this);
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
        }

        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initFromIntent(Intent intent);

    protected abstract int initLayout();
}
