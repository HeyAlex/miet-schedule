package com.alex.miet.mobile.news;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.alex.miet.mobile.api.UniversityApiFactory;
import com.alex.miet.mobile.data.news.NewsRepository;
import com.alex.miet.mobile.entities.NewsItem;
import com.alex.miet.mobile.model.news.Article;
import com.alex.miet.mobile.model.news.ArticleResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/*package*/ class NewsPresenterImpl implements NewsPresenter {

    private final CompositeDisposable newsResponseSubscription = new CompositeDisposable();
    private NewsView view;
    private NewsRepository newsRepository;

    @Inject
    /*package*/ NewsPresenterImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        Timber.d("NewsPresenterImpl");
    }

    @Override
    public void onNewsClicked(NewsItem newModel) {

    }

    @Override
    public void onRefreshRequest() {
        Timber.d("onRefreshRequest");
        newsResponseSubscription.add(UniversityApiFactory.getUniversityApi().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseNewsSubscriber()));
    }

    @Override
    public void showNews() {

    }

    @Override
    public void onViewAttached(NewsView view) {
        Timber.d("onViewAttached");
        this.view = view;
        this.view.setRefreshing(newsResponseSubscription.size() != 0);
        this.view.showNews(newsRepository.getAll().blockingGet());
    }

    @Override
    public void onViewDetached() {
        Timber.d("onViewDetached");
        this.view = null;
        newsResponseSubscription.dispose();
    }


    private class ResponseNewsSubscriber extends DisposableSingleObserver<ArticleResponse> {

        final DateTimeFormatter dtf = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss Z")
                .withLocale(Locale.ENGLISH);

        /*package*/ ResponseNewsSubscriber() {
        }

        @Override
        public void onSuccess(ArticleResponse articleResponse) {
            newsRepository.deleteAll();
            List<NewsItem> newsModelList = transfromResponseToDaoModel(articleResponse);
            newsRepository.saveAll(newsModelList);
            newsResponseSubscription.clear();
            if (view != null) {
                view.setRefreshing(false);
                view.showNews(newsModelList);
            }
        }

        @Override
        public void onError(Throwable t) {
            newsResponseSubscription.clear();
            if (view != null) {
                view.setRefreshing(false);
                view.showErrorView();
            }
        }

        private List<NewsItem> transfromResponseToDaoModel(ArticleResponse response) {
            List<NewsItem> models = new ArrayList<>();

            for (Article article : response.getAllArticles()) {
                NewsItem model = new NewsItem(null, article.getTitle(),
                        dtf.parseDateTime(article.getPubDate()).toString("d MMMM yyyy HH:mm"),
                        article.getLink(), article.getEnclosure().getUrl(), article.getDescription());
                models.add(model);
            }

            return models;
        }
    }
}
