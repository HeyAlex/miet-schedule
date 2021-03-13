package com.alex.miet.mobile.news;

import com.alex.miet.mobile.api.UniversityApiFactory;
import com.alex.miet.mobile.data.news.NewsRepository;
import com.alex.miet.mobile.entities.NewsItem;
import com.alex.miet.mobile.model.news.Article;
import com.alex.miet.mobile.model.news.ArticleResponse;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/*package*/ class NewsPresenterImpl implements NewsPresenter {

    private CompositeDisposable newsResponseSubscription = null;
    private NewsView view;
    private NewsRepository newsRepository;

    @Inject
        /*package*/ NewsPresenterImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void onNewsClicked(NewsItem newModel) {

    }

    @Override
    public void onRefreshRequest() {
        newsResponseSubscription.add(UniversityApiFactory.getUniversityApi().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<ArticleResponse, List<NewsItem>>() {
                    @Override
                    public List<NewsItem> apply(ArticleResponse articleResponse) {
                        newsRepository.deleteAll();
                        List<NewsItem> newsModelList = transfromResponseToDaoModel(articleResponse);
                        newsRepository.saveAll(newsModelList);
                        return newsModelList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseNewsSubscriber()));
    }

    @Override
    public void showNews() {

    }

    @Override
    public void onViewAttached(final NewsView view) {
        newsResponseSubscription = new CompositeDisposable();
        this.view = view;
        this.view.setRefreshing(newsResponseSubscription.size() != 0);
        newsResponseSubscription.add(newsRepository.getAll().subscribeWith(new DisposableMaybeObserver<List<NewsItem>>() {
            @Override
            public void onSuccess(List<NewsItem> listMaybe) {
                view.showNews(listMaybe);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }));
    }

    @Override
    public void onViewDetached() {
        Timber.d("onViewDetached");
        this.view = null;
        newsResponseSubscription.dispose();
    }

    private class ResponseNewsSubscriber extends DisposableSingleObserver<List<NewsItem>> {


        /*package*/ ResponseNewsSubscriber() {
        }

        @Override
        public void onSuccess(List<NewsItem> articleResponse) {
            if (view != null) {
                view.setRefreshing(false);
                view.showNews(articleResponse);
            }
            newsResponseSubscription.clear();
        }

        @Override
        public void onError(Throwable t) {
            newsResponseSubscription.clear();
            if (view != null) {
                view.setRefreshing(false);
                view.showErrorView();
            }
        }
    }

    final DateTimeFormatter dtf = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss Z")
            .withLocale(Locale.ENGLISH);


    public List<NewsItem> transfromResponseToDaoModel(ArticleResponse response) {
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
