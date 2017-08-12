package heyalex.com.miet_schedule.news;

import java.util.List;

import heyalex.com.miet_schedule.NewsModel;

public interface NewsView {

    void showNews(List<NewsModel> news);

    void showErrorView();

    void setRefreshing(boolean refreshing);
}
