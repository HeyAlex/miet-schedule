package com.hey.mietunoff.mietunofficial.news;

import com.hey.mietunoff.mietunofficial.util.BasePresenter;

import heyalex.com.miet_schedule.NewsModel;

/**
 * Created by mac on 28.04.17.
 */

public interface NewsPresenter extends BasePresenter<NewsView> {

    void onNewsClicked(NewsModel newModel);

    void onRefreshRequest();

    void showNews();
}
