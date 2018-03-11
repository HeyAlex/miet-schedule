package com.alex.miet.mobile.news;

import java.util.List;

import com.alex.miet.mobile.NewsModel;

public interface NewsView {

    void showNews(List<NewsModel> news);

    void showErrorView();

    void setRefreshing(boolean refreshing);
}
