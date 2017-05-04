package heyalex.com.miet_schedule.news;

import java.util.List;

import heyalex.com.miet_schedule.NewsModel;

/**
 * Created by mac on 28.04.17.
 */

public interface NewsView {
    void onClickDetailNews(NewsModel news);
    void showNews(List<NewsModel> news);
    void showErrorView();
    void setRefreshing(boolean refreshing);
}
