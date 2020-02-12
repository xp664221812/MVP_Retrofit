package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

public interface MainContract {

    interface View extends IView {
    }

    interface Presenter  {
    }

    interface Model extends IModel {
    }

    interface IComicCallBack {
        void ok(HttpResult result);
    }

}



