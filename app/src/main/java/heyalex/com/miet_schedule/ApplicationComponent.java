package heyalex.com.miet_schedule;


import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import heyalex.com.miet_schedule.api.UniversityApiModule;
import heyalex.com.miet_schedule.api.UniversityService;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenterImpl;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavDrawerModule.class,
        UniversityApiModule.class
})

public interface ApplicationComponent {

    UniversityService getUniversityApi();

    NavDrawerPresenter getHeaderPresenter();
}
