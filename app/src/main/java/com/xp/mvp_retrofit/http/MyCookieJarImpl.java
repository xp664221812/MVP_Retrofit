package com.xp.mvp_retrofit.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MyCookieJarImpl implements CookieJar {
    private final PersistentCookieStore cookieStore = PersistentCookieStore.getInstance();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        //本地可校验cookie，并根据需要存储
        if (TextUtils.equals(url.toString(), "https://www.wanandroid.com/user/login")) {
            ToastUtils.showShort("true");
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (TextUtils.equals(url.toString(), "https://www.wanandroid.com/user/login")) {
            ToastUtils.showShort("true");
            return null;
        }
        //从本地拿取需要的cookie
        return cookieStore.get(url);
    }
}

