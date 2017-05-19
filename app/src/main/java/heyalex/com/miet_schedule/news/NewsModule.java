package heyalex.com.miet_schedule.news;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.news.NewsRepository;

/**
 * Created by mac on 28.04.17.
 */

@Module
public class NewsModule {

    @Provides
    @NewsScope
        /*package*/ NewsPresenter provideNewsPresenter(NewsRepository newsRepository) {
        return new NewsPresenterImpl(newsRepository);
    }
}
