package heyalex.com.miet_schedule;


import javax.inject.Singleton;

import dagger.Component;
import heyalex.com.miet_schedule.api.UniversityApiModule;
import heyalex.com.miet_schedule.api.UniversityService;
import heyalex.com.miet_schedule.data.DataModule;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.news.NewsModule;
import heyalex.com.miet_schedule.news.NewsPresenter;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavDrawerModule.class,
        UniversityApiModule.class,
        DataModule.class
})

public interface ApplicationComponent {

    UniversityService getUniversityApi();
    NavDrawerPresenter getHeaderPresenter();
    NewsRepository newsRepository();
}
