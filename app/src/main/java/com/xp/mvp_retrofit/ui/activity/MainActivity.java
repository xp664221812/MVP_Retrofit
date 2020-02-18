package com.xp.mvp_retrofit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.google.android.material.navigation.NavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.R2;
import com.xp.mvp_retrofit.base.BaseMVPActivity;
import com.xp.mvp_retrofit.constant.Constants;
import com.xp.mvp_retrofit.event.LoginEvent;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.mvp.presenter.MainPresenter;
import com.xp.mvp_retrofit.storage.beans.HttpResult;
import com.xp.mvp_retrofit.storage.beans.UserInfoBody;
import com.xp.mvp_retrofit.ui.fragment.HomeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;


public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {


    public static final String BOTTOM_INDEX = "bottom_index";


    private final static int FRAGMENT_HOME = 0x01;
    private final int FRAGMENT_SQUARE = 0x02;
    private final int FRAGMENT_WECHAT = 0x03;
    private final int FRAGMENT_SYSTEM = 0x04;
    private final int FRAGMENT_PROJECT = 0x05;

    private int currentIndex = FRAGMENT_HOME;

    //https://api.github.com/repos/jetbrains/Kotlin/contributors
    @BindView(R2.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R2.id.nav_view)
    NavigationView navigationView;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.bottom_navigation)
    BottomNavigationViewEx bottomNavigationView;


    private View headView;
    private TextView username;
    private TextView userInfo;

    private TextView mScore;


    private HomeFragment homeFragment;


    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.global_action_open, R.string.global_action_close);
        toggle.syncState();


        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(BOTTOM_INDEX, currentIndex);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData(@androidx.annotation.Nullable Bundle savedInstanceState) {


        requestPermission();


        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(BOTTOM_INDEX);
        }

        headView = navigationView.getHeaderView(0);
        username = headView.findViewById(R.id.tv_name);
        userInfo = headView.findViewById(R.id.tv_user_info);

        headView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });


        mScore = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.point));
        mScore.setGravity(Gravity.CENTER_VERTICAL);
        mScore.setTextSize(12);
        mScore.setTextColor(ContextCompat.getColor(this, R.color.text_color));

        bottomNavigationView.setSmallTextSize(12);
        bottomNavigationView.setLargeTextSize(12);
        bottomNavigationView.setIconSize(22, 22);
        bottomNavigationView.setIconsMarginTop(SizeUtils.dp2px(8));
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.enableAnimation(false);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentUtils.hide(getSupportFragmentManager());

        switch (currentIndex) {
            case FRAGMENT_HOME:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    FragmentUtils.add(getSupportFragmentManager(), homeFragment, R.id.container);
                } else {
                    FragmentUtils.show(homeFragment);
                }

                break;
            default:
                break;
        }

        if (SPUtils.getInstance().getBoolean(Constants.ISLOGIN)) {
            username.setText(SPUtils.getInstance().getString(Constants.USERNAME));
        }


    }

    @Override
    protected void initFromIntent(Intent intent) {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getUserInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void requestPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE).rationale(new PermissionUtils.OnRationaleListener() {
            @Override
            public void rationale(ShouldRequest shouldRequest) {
                shouldRequest.again(true);
            }
        }).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                return;
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                if (!permissionsDenied.isEmpty() || !permissionsDeniedForever.isEmpty()) {
                    finish();
                }
            }
        }).theme(activity -> {
        }).request();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventLoginResult(LoginEvent event) {
        if (event.isLogin) {
            username.setText(SPUtils.getInstance().getString(Constants.USERNAME));
            showToast("登陆成功了");
        } else {


//            SPUtils.getInstance().clear();
//            SPUtils.getInstance().put(Constants.ISLOGIN, true);
//            SPUtils.getInstance().put(Constants.USERNAME, loginData.getUsername());
//            SPUtils.getInstance().put(Constants.TOKEN, loginData.getToken());
//            EventBus.getDefault().post(new LoginEvent(true));
        }
    }

    @Override
    public void showUserInfo(HttpResult<UserInfoBody> result) {
        UserInfoBody bean = result.data;
        mScore.setText(String.valueOf(bean.getCoinCount()));
        String level = (bean.getCoinCount() / 100 + 1) + "";
        String msg = String.format(getString(R.string.level_and_rank), level, bean.getRank() + "");
        userInfo.setText(msg);
    }
}
