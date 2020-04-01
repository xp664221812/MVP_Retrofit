package com.xp.mvp_retrofit.mvp.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.event.CollectEvent;
import com.xp.mvp_retrofit.mvp.contract.CommonContract;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.widget.DefaultObserver;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CommonPresenter<M extends CommonContract.Model, V extends CommonContract.View> extends BasePresenter<M, V>
        implements CommonContract.Presenter<V> {

    @Override
    public M createModel() {
        return null;
    }


    @Override
    public void addCollectArticle(int id) {
        mModel.addCollectArticle(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<HttpResult<Object>>(rootView) {

                    @Override
                    public void onSuccess(HttpResult<Object> result) {
                        EventBus.getDefault().post(new CollectEvent(true, id));
                    }
                });
    }

    @Override
    public void cancelCollectArticle(int id) {
        mModel.cancelCollectArticle(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<HttpResult<Object>>(rootView) {

                    @Override
                    public void onSuccess(HttpResult<Object> result) {
                        EventBus.getDefault().post(new CollectEvent(false, id));
                    }
                });
    }


}
