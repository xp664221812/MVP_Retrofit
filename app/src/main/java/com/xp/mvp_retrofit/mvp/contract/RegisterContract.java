package com.xp.mvp_retrofit.mvp.contract;

import com.xp.mvp_retrofit.base.IModel;
import com.xp.mvp_retrofit.base.IPresenter;
import com.xp.mvp_retrofit.base.IView;

public interface RegisterContract {

    interface View extends IView {

    }

    interface Presenter  {
        void register(String username,String password,String repassword);
    }

    interface Model extends IModel {
        void register(String username,String password,String repassword,IComicCallBack callBack);
    }

    interface IComicCallBack {
        void ok(boolean result);
    }

}



