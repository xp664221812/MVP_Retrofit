package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.RegisterContract;
import com.xp.mvp_retrofit.mvp.model.RegisterModel;
import com.xp.mvp_retrofit.storage.beans.HttpResult;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    RegisterModel model;


    public RegisterPresenter() {
        model = new RegisterModel();
    }


    @Override
    public void register(String username, String password, String repassword) {
        model.register(username, password, repassword, new RegisterContract.IComicCallBack() {
            @Override
            public void ok(HttpResult result) {
                rootView.register(result);
            }
        });
    }
}
