package heyalex.com.miet_schedule.news;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.api.UniversityApiFactory;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.model.news.Article;
import heyalex.com.miet_schedule.model.news.ArticleResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mac on 02.05.17.
 */

/*package*/ class NewsPresenterImpl implements NewsPresenter {

    private NewsView view;
    private NewsRepository newsRepository;
    private final CompositeDisposable newsResponseSubscription = new CompositeDisposable();

    @Inject
    /*package*/ NewsPresenterImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        Timber.i("new object SchedulePresenterImpl, and CompositeDisposable size isL %s", newsResponseSubscription.size());
    }

    @Override
    public void onNewsClicked(NewsModel newModel) {

    }

    @Override
    public void onRefreshRequest() {
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
        this.view = view;
        this.view.setRefreshing(newsResponseSubscription.size() != 0);
        this.view.showNews(newsRepository.getAll());
    }

    @Override
    public void onViewDetached() {
        this.view = null;
        newsResponseSubscription.dispose();
    }


    private class ResponseNewsSubscriber extends DisposableObserver<ArticleResponse> {

        /*package*/ ResponseNewsSubscriber() {
        }

        final DateTimeFormatter dtf = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss Z")
                .withLocale(Locale.ENGLISH);

        @Override
        public void onNext(ArticleResponse articleResponse) {
            newsRepository.deleteAll();
            List<NewsModel> newsModelList = transfromResponseToDaoModel(articleResponse);
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

        @Override
        public void onComplete() {

        }

        private List<NewsModel> transfromResponseToDaoModel(ArticleResponse response) {
            List<NewsModel> models = new ArrayList<>();

            for (Article article : response.getAllArticles()) {
                NewsModel model = new NewsModel();
                model.setDate(dtf.parseDateTime(article.getPubDate()).toString("d MMMM yyyy HH:mm"));
                model.setDescription(article.getDescription());
                model.setImageUrl(article.getEnclosure().getUrl());
                model.setLink(article.getLink());
                model.setTitle(article.getTitle());
                models.add(model);
            }

            return models;
        }
    }
}
