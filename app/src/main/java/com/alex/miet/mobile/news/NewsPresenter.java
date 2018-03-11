package com.alex.miet.mobile.news;

import com.alex.miet.mobile.util.BasePresenter;

import com.alex.miet.mobile.NewsModel;

/**
 * Created by mac on 28.04.17.
 */

public interface NewsPresenter extends BasePresenter<NewsView> {

    void onNewsClicked(NewsModel newModel);

    void onRefreshRequest();

    void showNews();
}
