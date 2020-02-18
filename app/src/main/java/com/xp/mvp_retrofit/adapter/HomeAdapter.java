package com.xp.mvp_retrofit.adapter;

import android.text.TextUtils;
import android.text.format.DateUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xp.mvp_retrofit.R;
import com.xp.mvp_retrofit.storage.beans.Article;
import com.xp.mvp_retrofit.storage.beans.Tag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @Nullable Article article) {


        helper.setGone(R.id.fresh, article.isFresh());

        helper.setGone(R.id.top, article.isTop());

        List<Tag> tags = article.getTags();
        helper.setGone(R.id.tag, tags.size() > 0);
        if (tags.size() > 0) {
            helper.setText(R.id.tag, article.getTags().get(0).getName());
        }

        helper.setText(R.id.title, article.getTitle());

        helper.setText(R.id.date, article.getNiceDate());

        String author = article.getAuthor();
        if (TextUtils.isEmpty(author)) {
            author = article.getShareUser();
        }
        helper.setText(R.id.author, author);

        String chapterName = article.getSuperChapterName() + " / " + article.getChapterName();
        helper.setText(R.id.chapterName, chapterName);

    }
}
