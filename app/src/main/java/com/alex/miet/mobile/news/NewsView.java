package com.alex.miet.mobile.news;

import java.util.List;

import com.alex.miet.mobile.entities.NewsItem;

public interface NewsView {

    void showNews(List<NewsItem> news);

    void showErrorView();

    void setRefreshing(boolean refreshing);
}
