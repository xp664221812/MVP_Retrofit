package com.xp.mvp_retrofit.http.interceptor;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {
            request.newBuilder().cacheControl(CacheControl.FORCE_CACHE);
        }

        Response response = chain.proceed(request);

        if (NetworkUtils.isConnected()) {
            int maxAge = 60 * 3;
            response.newBuilder()
                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Retrofit")
                    .build();

        } else {
            // 无网络时，设置超时为4周  只对get有用,post没有缓冲
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("nyn")
                    .build();
        }


        return response;
    }
}
