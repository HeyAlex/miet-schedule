package com.alex.miet.mobile.news;

import com.alex.miet.mobile.entities.NewsItem;
import com.alex.miet.mobile.util.BasePresenter;

public interface NewsPresenter extends BasePresenter<NewsView> {

    void onNewsClicked(NewsItem newModel);

    void onRefreshRequest();

    void showNews();
}
