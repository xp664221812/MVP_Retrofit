package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.mvp.model.HomeModel;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.ArticleResponseBody;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.widget.DefaultObserver;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends CommonPresenter<HomeContract.Model,HomeContract.View> implements HomeContract.Presenter {


    @Override
    public HomeContract.Model createModel() {
        return new HomeModel();
    }


    @Override
    public void requestHomeData() {
        requestBanner();

        Observable.zip(mModel.requestTopArticles(), mModel.requestArticles(0), (result1, result2) -> {
            for (Article article : result1.data) {
                article.setTop(true);
            }

            result2.data.getDatas().addAll(0, result1.data);
            return result2;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<HttpResult<ArticleResponseBody>>(rootView) {
                    @Override
                    public void onSuccess(HttpResult<ArticleResponseBody> articles) {
                        rootView.showArticles(articles.data);
                    }
                });

//        Observable.zip(model.requestTopArticles(), model.requestArticles(0), (result1, result2) -> {
//            for (Article article : result1.data) {
//                article.setTop(true);
//            }
//
//            result2.data.getDatas().addAll(0, result1.data);
//            return result2;
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HttpResult<ArticleResponseBody>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<ArticleResponseBody> articles) {
//                rootView.showArticles(articles.data);
//
////                rootView.hideLoading();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });
    }

    @Override
    public void requestBanner() {

        mModel.requestBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<HttpResult<List<Banner>>>(rootView) {
                    @Override
                    public void onSuccess(HttpResult<List<Banner>> result) {
                        rootView.showBanner(result.data);
                    }
                });

//        model.requestBanner().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<HttpResult<List<Banner>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
////                        if (!NetworkUtils.isConnected()) {
////                            onComplete();
////                        }
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<List<Banner>> result) {
//                        rootView.showBanner(result.data);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        rootView.hideLoading();
//                    }
//
//                    @Override
//                    public void onComplete() {
////                        rootView.hideLoading();
//                    }
//                });


    }

    @Override
    public void requestArticles(int num) {
        mModel.requestArticles(num).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<HttpResult<ArticleResponseBody>>(rootView) {
                    @Override
                    public void onSuccess(HttpResult<ArticleResponseBody> result) {
                        rootView.showArticles(result.data);
                    }
                });


//        model.requestArticles(num).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HttpResult<ArticleResponseBody>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<ArticleResponseBody> result) {
//                rootView.showArticles(result.data);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }


}
