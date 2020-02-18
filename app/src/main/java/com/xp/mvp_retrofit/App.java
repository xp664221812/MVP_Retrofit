package com.xp.mvp_retrofit;

import android.app.Application;
import android.content.Context;
import android.net.wifi.aware.PublishConfig;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.xp.mvp_retrofit.constant.Constants;

import androidx.multidex.MultiDex;
import butterknife.ButterKnife;

public class App extends Application {

    private static App application;


    public static Context getContext() {
        return application;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);



        // 安装tinker
        Beta.installTinker();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Utils.init(this);
        LogUtils.getConfig().setGlobalTag("Learn:");

        initBugly();

    }


    private void initBugly() {
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, Constants.BUGLY_ID, true);
    }

}