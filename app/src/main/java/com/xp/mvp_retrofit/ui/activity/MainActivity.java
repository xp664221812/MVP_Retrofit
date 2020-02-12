package com.xp.mvp_retrofit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.google.android.material.navigation.NavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.R2;
import com.xp.mvp_retrofit.base.BaseMVPActivity;
import com.xp.mvp_retrofit.mvp.contract.MainContract;
import com.xp.mvp_retrofit.mvp.presenter.MainPresenter;
import com.xp.mvp_retrofit.ui.fragment.HomeFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
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

    private HomeFragment homeFragment;


    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.global_action_open, R.string.global_action_close);
        toggle.syncState();


        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
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
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(BOTTOM_INDEX);
        }

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

    }

    @Override
    protected void initFromIntent(Intent intent) {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
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
    public void handleError(Exception e) {

    }

}
