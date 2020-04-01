package com.xp.mvp_retrofit.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.R2;
import com.xp.mvp_retrofit.adapter.HomeAdapter;
import com.xp.mvp_retrofit.base.BaseMVPFragment;
import com.xp.mvp_retrofit.constant.Constants;
import com.xp.mvp_retrofit.event.CollectEvent;
import com.xp.mvp_retrofit.mvp.contract.HomeContract;
import com.xp.mvp_retrofit.mvp.presenter.HomePresenter;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.ArticleResponseBody;
import com.xp.mvp_retrofit.storage.beans.Banner;
import com.xp.mvp_retrofit.ui.activity.ContentActivity;
import com.xp.mvp_retrofit.ui.activity.LoginActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;

public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View {
    private HomeAdapter mAdapter;
    private List<Article> mDataSource = new LinkedList<>();

    private BGABanner banner;

    @BindView(R2.id.rv_list)
    RecyclerView mRecyclerView;

    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R2.id.multiple_status_view)
    MultipleStatusView statusView;

    private boolean isRefresh = true;

    private int currentPage = 1;
//    int totalPage;


    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(refreshListener);


        if (mAdapter == null) {
            mAdapter = new HomeAdapter(R.layout.item_home_list, mDataSource);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            divider.setDrawable(mContext.getDrawable(R.color.gray));
            mRecyclerView.addItemDecoration(divider);

            View header = View.inflate(mContext, R.layout.item_home_banner, null);
            banner = header.findViewById(R.id.banner);
            mAdapter.addHeaderView(header);
            mAdapter.setOnLoadMoreListener(loadMoreListener, mRecyclerView);

            mAdapter.setOnItemClickListener(onItemClickListener);

            mAdapter.setOnItemChildClickListener(onItemChildClickListener);

            mRecyclerView.setAdapter(mAdapter);
        }


        statusView.setOnRetryClickListener(v ->
                lazyLoad());

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_refresh_layout;
    }

    @Override
    protected void lazyLoad() {
        showLoading();
        mPresenter.requestHomeData();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showLoading() {
        statusView.showLoading();

    }

    @Override
    public void hideLoading() {
        if (mAdapter.getData().isEmpty()) {
            statusView.showEmpty();
        } else {
            statusView.showContent();
        }
        refreshLayout.setRefreshing(false);
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void showError(String error) {
        statusView.showError();

    }

    @Override
    public void showMsg(String msg) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCollectResult(CollectEvent event) {
        int id = event.id;
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            Article article = mDataSource.get(i);
            if (article.getId() == id) {
                if (event.isCollect) {
                    article.setCollect(true);
                    ToastUtils.showShort("收藏成功");
                } else {
                    article.setCollect(false);
                    ToastUtils.showShort("已取消收藏");
                }
               mAdapter.notifyDataSetChanged();
                return;
            }
        }
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

    @Override
    public void showArticles(ArticleResponseBody responseBody) {
//        LogUtils.d("response " + responseBody.);
        currentPage = responseBody.getCurPage();

        if (isRefresh) {
            mAdapter.replaceData(responseBody.getDatas());
        } else {
            mAdapter.addData(responseBody.getDatas());
        }

        if (currentPage < responseBody.getPageCount()) {
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }


    }

    private BGABanner.Adapter<ImageView, String> bannerAdapter = (banner, itemView, model, position) -> {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).
                placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
        Glide.with(HomeFragment.this).applyDefaultRequestOptions(options).load(model).into(itemView);
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isRefresh = true;
            mAdapter.setEnableLoadMore(false);
            mPresenter.requestHomeData();
        }
    };

    private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener = () -> {
        refreshLayout.setRefreshing(false);
        isRefresh = false;
        mPresenter.requestArticles(currentPage);
    };

    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(mContext, ContentActivity.class);
            Article article = mAdapter.getItem(position);
            intent.putExtra("link", article.getLink());
            startActivity(intent);
        }
    };


    private BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener = (adapter, view, position) -> {
        switch (view.getId()) {
            case R.id.collect:
                Article article = mAdapter.getItem(position);
                if (SPUtils.getInstance().getBoolean(Constants.ISLOGIN)) {
                    if (article.isCollect()) {
                        mPresenter.cancelCollectArticle(article.getId());
                    } else {
                        mPresenter.addCollectArticle(article.getId());
                    }
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    };


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public boolean useEventBus() {
        return true;
    }
}
