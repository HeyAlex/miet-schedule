package heyalex.com.miet_schedule.news;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.api.UniversityApiFactory;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenterImpl;

/**
 * Created by mac on 28.04.17.
 */

@Module
public class NewsModule{
        @Singleton
        @Provides
        public NewsPresenter provideNewsPresenter() {
            return new NewsPresenterImpl();
        }
}
