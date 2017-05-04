package heyalex.com.miet_schedule.news;

import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.mvp.BasePresenter;

/**
 * Created by mac on 28.04.17.
 */

public interface NewsPresenter extends BasePresenter<NewsView>{
    void onNewsClicked(NewsModel newModel);
    void onRefreshRequest();
    void showNews();
}
