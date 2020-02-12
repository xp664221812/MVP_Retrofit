package com.xp.mvp_retrofit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.base.BaseActivity;

import androidx.annotation.Nullable;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        new Handler().postDelayed(() -> {
            ActivityUtils.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000);
    }

    @Override
    protected void initFromIntent(Intent intent) {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_splash;
    }
}
