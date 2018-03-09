package com.hey.mietunoff.mietunofficial.news;

import java.util.List;

import com.hey.mietunoff.mietunofficial.NewsModel;

public interface NewsView {

    void showNews(List<NewsModel> news);

    void showErrorView();

    void setRefreshing(boolean refreshing);
}
