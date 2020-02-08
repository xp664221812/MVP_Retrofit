package com.xp.mvp_retrofit;

import android.os.Bundle;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    //https://api.github.com/repos/jetbrains/Kotlin/contributors
    DrawerLayout layout;

    NavigationView navigationView;
    RecyclerView mRecyclerView;
    Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_list);
//        layout=findViewById(R.id.drawer);
//        layout.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//                LogUtils.d("1111111111111111111111");
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View drawerView) {
//                LogUtils.d("2222222222222222222222");
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View drawerView) {
//                LogUtils.d("333333333333333333333");
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                LogUtils.d("44444444444444444444444");
//            }
//        });

        layout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_view);

//        layout.openDrawer(Gravity.LEFT);
        List<String> list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("index " + i);
        }

        if (mAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mAdapter = new Adapter(R.layout.stringitem, list);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,layout,R.string.app_name,R.string.app_name);
        toggle.syncState();
        layout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });


//        Api api = AppClient.initRetrofit().create(Api.class);
//
//        api.contributors("jetbrains", "Kotlin").enqueue(new Callback<List<Contributor>>() {
//            @Override
//            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                List<Contributor> list = response.body();
//                LogUtils.d(list.size());
//            }
//
//
//            @Override
//            public void onFailure(Call<List<Contributor>> call, Throwable t) {
//                LogUtils.d(t.getCause());
//            }
//        });


    }


    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public Adapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable String s) {
            baseViewHolder.setText(R.id.tv, s);
        }
    }

}
