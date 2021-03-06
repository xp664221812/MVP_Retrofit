package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.UserInfoBody;

import io.reactivex.Observable;

public interface MainContract {

    interface View extends IView {
        void showUserInfo(HttpResult<UserInfoBody> result);
    }

    interface Presenter {
        void getUserInfo();
    }

    interface Model extends IModel {
        Observable<HttpResult<UserInfoBody>> getUserInfo();
    }

    interface IComicCallBack {
        void ok(HttpResult result);
    }

}



