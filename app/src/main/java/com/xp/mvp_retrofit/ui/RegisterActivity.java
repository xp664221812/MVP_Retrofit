package com.xp.mvp_retrofit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.base.BaseMVPActivity;
import com.xp.mvp_retrofit.mvp.contract.RegisterContract;
import com.xp.mvp_retrofit.mvp.presenter.RegisterPresenter;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMVPActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.et_username)
    MaterialEditText mUsername;

    @BindView(R.id.et_password)
    MaterialEditText mPassword;

    @BindView(R.id.et_repassword)
    MaterialEditText mRepassword;

    @BindView(R.id.bt_register)
    Button register;


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        LogUtils.d(mUsername==null);
    }

    @Override
    protected void initFromIntent(Intent intent) {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.bt_register)
    public void register(View view) {
        LogUtils.d("111111111111");
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String repassword = mRepassword.getText().toString();
        mPresenter.register(username, password, repassword);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleError(Exception e) {

    }
}
