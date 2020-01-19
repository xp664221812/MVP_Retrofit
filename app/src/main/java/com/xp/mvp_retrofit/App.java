package com.xp.mvp_retrofit;

import android.app.Application;
import android.content.Context;
import android.net.wifi.aware.PublishConfig;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

public class App extends Application {

    private static App application;


    public Context getContext() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Utils.init(this);
        LogUtils.getConfig().setGlobalTag("clear:");
    }
}
