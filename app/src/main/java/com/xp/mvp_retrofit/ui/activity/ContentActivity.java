package com.xp.mvp_retrofit.ui.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.base.BaseMVPActivity;
import com.xp.mvp_retrofit.mvp.contract.ContentContract;
import com.xp.mvp_retrofit.mvp.presenter.ContentPresenter;

import java.net.URISyntaxException;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;

public class ContentActivity extends BaseMVPActivity<ContentPresenter> implements ContentContract.View {
    private String link;

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected ContentPresenter createPresenter() {
        return new ContentPresenter();
    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    //处理intent协议
                    if (url.startsWith("intent://")) {
                        Intent intent;
                        try {
                            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                            intent.addCategory("android.intent.category.BROWSABLE");
                            intent.setComponent(null);
                            intent.setSelector(null);
                            List<ResolveInfo> resolves = getPackageManager().queryIntentActivities(intent, 0);
                            if (resolves.size() > 0) {
                                startActivityIfNeeded(intent, -1);
                            }
                            return true;
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    // 处理自定义scheme协议
                    if (!url.startsWith("http")) {
                        LogUtils.d("处理自定义scheme-->" + url);
                        try {
                            // 以下固定写法
                            final Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(url));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        } catch (Exception e) {
                            // 防止没有安装的情况
                            e.printStackTrace();
                            ToastUtils.showShort("您所打开的第三方App未安装！");
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
//        webView.setWebChromeClient(new WebChromeClient());


        webView.getSettings().setJavaScriptEnabled(true);


        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.getSettings().setLoadWithOverviewMode(true);

        //设置可以支持缩放

        webView.getSettings().setSupportZoom(true);

        //扩大比例的缩放

        webView.getSettings().setUseWideViewPort(true);

        //设置是否出现缩放工具

        webView.getSettings().setBuiltInZoomControls(true);

        webView.getSettings().setDomStorageEnabled(true);
    }


    @Override
    protected void initFromIntent(Intent intent) {
        link = intent.getStringExtra("link");

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_content;
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
    public void showMsg(String msg) {

    }


}
