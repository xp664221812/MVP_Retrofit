package com.xp.mvp_retrofit.widget;

import android.net.ParseException;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.JsonParseException;
import com.xp.mvp_retrofit.App;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.BaseBean;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class DefaultObserver<T extends BaseBean> implements Observer<T> {
    private IView view;

    private boolean showLoading;

    public DefaultObserver(IView view) {
        this.view = view;
//        this.showLoading = showLoading;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (showLoading) {
            view.showLoading();
        }
        if (!NetworkUtils.isConnected()) {
            view.showToast(App.getContext().getString(R.string.no_network_tip));
            onComplete();
        }
    }


    @Override
    public void onNext(T t) {
        if (t.errorCode == 0) {
            onSuccess(t);
        } else {

            view.showToast(t.errorMsg);
        }

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {     //   HTTP错误

        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误

        } else if (e instanceof InterruptedIOException) {   //  连接超时

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误

        } else {

        }
//        view.showToast(e.getMessage());
        view.hideLoading();

//        LogUtils.d(e.getMessage());
        LogUtils.d("onComplete----------------");
    }

    @Override
    public void onComplete() {
        view.hideLoading();
        LogUtils.d("onError----------------");
//        view = null;
    }

    public abstract void onSuccess(T t);

}

