package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.ArticleResponseBody;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {

    interface View extends CommonContract.View {
        void showBanner(List<Banner> list);

        void showArticles(ArticleResponseBody responseBody);
    }

    interface Presenter extends CommonContract.Presenter<View> {
        void requestHomeData();

        void requestBanner();

        void requestArticles(int num);

    }

    interface Model extends CommonContract.Model {
        Observable<HttpResult<List<Banner>>> requestBanner();

        Observable<HttpResult<List<Article>>> requestTopArticles();


        Observable<HttpResult<ArticleResponseBody>> requestArticles(int num);

    }


}



