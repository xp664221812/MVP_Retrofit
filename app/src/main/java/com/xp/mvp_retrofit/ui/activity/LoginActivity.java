package com.xp.mvp_retrofit.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.R2;
import com.xp.mvp_retrofit.base.BaseMVPActivity;
import com.xp.mvp_retrofit.constant.Constants;
import com.xp.mvp_retrofit.event.LoginEvent;
import com.xp.mvp_retrofit.mvp.contract.LoginContract;
import com.xp.mvp_retrofit.mvp.presenter.LoginPresenter;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.LoginData;
import com.xp.mvp_retrofit.utils.DialogUtil;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R2.id.et_username)
    MaterialEditText mUsername;

    @BindView(R2.id.et_password)
    MaterialEditText mPassword;


//    @BindView(R.id.bt_register)
//    Button register;


    private ProgressDialog mDialog;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
//        showLoading();
        mDialog = DialogUtil.getWaittingDialog(this, "正在登陆");
    }

    @Override
    protected void initFromIntent(Intent intent) {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R2.id.bt_login)
    public void login(View view) {
        showLoading();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        mPresenter.login(username, password);
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.hide();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showMsg(String msg) {

    }


    @Override
    public void loginSuccess(HttpResult<LoginData> result) {
        LoginData loginData = result.data;
        SPUtils.getInstance().put(Constants.ISLOGIN, true);
        SPUtils.getInstance().put(Constants.USERNAME, loginData.getUsername());
        SPUtils.getInstance().put(Constants.TOKEN, loginData.getToken());
        EventBus.getDefault().post(new LoginEvent(true));
        finish();
    }
}
