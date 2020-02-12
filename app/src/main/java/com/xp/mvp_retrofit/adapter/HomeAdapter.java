package com.xp.mvp_retrofit.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xp.mvp_retrofit.storage.beans.Article;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable Article article) {

    }
}
