package com.xp.mvp_retrofit.mvp.presenter;

import com.xp.mvp_retrofit.base.BasePresenter;
import com.xp.mvp_retrofit.mvp.contract.ContentContract;
import com.xp.mvp_retrofit.mvp.model.ContentModel;

public class ContentPresenter extends BasePresenter<ContentContract.View> implements ContentContract.Presenter {
    ContentModel model;


    public ContentPresenter() {
        model = new ContentModel();
    }


}
