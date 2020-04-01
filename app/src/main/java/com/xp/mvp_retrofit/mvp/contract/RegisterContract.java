package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IPresenter;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

public interface RegisterContract {

    interface View extends IView {
        void register(HttpResult result);
    }

    interface Presenter extends IPresenter<View> {
        void register(String username, String password, String repassword);
    }

    interface Model extends IModel {
        void register(String username, String password, String repassword, IComicCallBack callBack);
    }

    interface IComicCallBack {
        void ok(HttpResult result);
    }

}



