package com.xp.mvp_retrofit.widget;

import android.net.ParseException;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonParseException;
import com.xp.mvp_retrofit.App;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.constant.Constants;
import com.xp.mvp_retrofit.storage.beans.BaseBean;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
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
            if (t.errorCode == -1001) {
                //需要重新登录
                view.showToast("检测到您未登录，请登录之后再操作");
                SPUtils.getInstance().put(Constants.ISLOGIN, false);
//                SPUtils.getInstance().put(Constants.TOKEN, "");
            } else {
                view.showToast(t.errorMsg);
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = "未知错误，可能代码写的不好！！";
        if (e instanceof HttpException
                || e instanceof SocketException
                || e instanceof ConnectException
                || e instanceof UnknownHostException) {     //   HTTP错误
            errorMsg = "网络异常，请稍等";

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误

            errorMsg = "数据解析异常！！";
        } else if (e instanceof IllegalArgumentException) {
            errorMsg = "参数错误！！";
        }
//        view.showToast(e.getMessage());

        view.hideLoading();
        view.showError(errorMsg);

//        LogUtils.d(e.getMessage());
        LogUtils.d("onComplete----------------");
    }

    @Override
    public void onComplete() {
        view.hideLoading();
    }

    public abstract void onSuccess(T t);

}

