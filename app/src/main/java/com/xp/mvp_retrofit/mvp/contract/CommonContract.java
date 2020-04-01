package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IPresenter;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import io.reactivex.Observable;

public interface CommonContract {

    interface View extends IView {
    }

    interface Presenter<V extends IView> extends IPresenter<V> {
        void addCollectArticle(int id);

        void cancelCollectArticle(int id);
    }

    interface Model extends IModel {
        Observable<HttpResult<Object>> addCollectArticle(int id);

        Observable<HttpResult<Object>> cancelCollectArticle(int id);
    }


}



