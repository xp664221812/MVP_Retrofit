package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IView;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.LoginData;

import io.reactivex.Observable;

public interface LoginContract {

    interface View extends IView {
        void loginSuccess(HttpResult<LoginData> result);
    }

    interface Presenter {
        void login(String username, String password);
    }

    interface Model extends IModel {
        Observable<HttpResult<LoginData>> login(String username, String password);
    }


}



