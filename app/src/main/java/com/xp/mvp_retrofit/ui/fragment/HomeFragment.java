package com.xp.mvp_retrofit.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.adapter.HomeAdapter;
import com.xp.mvp_retrofit.base.BaseMVPFragment;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.mvp.presenter.HomePresenter;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.widget.SpacesItemDecoration;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View {
    private HomeAdapter mAdapter;
    private List<Article> mDataSource = new LinkedList<>();

    private BGABanner banner;

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (mAdapter == null) {
            mAdapter = new HomeAdapter(R.layout.item_home_list, mDataSource);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
//            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(0));
            View header = View.inflate(activity, R.layout.item_home_banner, null);
            banner = header.findViewById(R.id.banner);
            mAdapter.addHeaderView(header);
            mRecyclerView.setAdapter(mAdapter);
        }
        mPresenter.requestBanner();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_refresh_layout;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
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

    @SuppressLint("CheckResult")
    @Override
    public void showBanner(List<Banner> list) {
        List<String> pathList = new LinkedList<>();
        List<String> titleList = new LinkedList<>();
        Observable.fromIterable(list).subscribe(banner -> {
            pathList.add(banner.getImagePath());
            titleList.add(banner.getTitle());

        });
        banner.setAutoPlayAble(pathList.size() > 1);
        banner.setData(pathList, titleList);
        banner.setAdapter(bannerAdapter);

    }

    private BGABanner.Adapter<ImageView, String> bannerAdapter = (banner, itemView, model, position) -> {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
        Glide.with(HomeFragment.this).applyDefaultRequestOptions(options).load(model).into(itemView);
    };

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}
