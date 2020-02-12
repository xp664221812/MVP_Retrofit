package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

import java.util.List;

public interface HomeContract {

    interface View extends IView {
        void showBanner(List<Banner> list);
    }

    interface Presenter {
        void requestBanner();
    }

    interface Model extends IModel {
        void requestBanner(IComicCallBack callBack);
    }

    interface IComicCallBack {
        void ok(HttpResult<List<Banner>> result);
    }

}



