package heyalex.com.miet_schedule;


import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import heyalex.com.miet_schedule.api.UniversityApi;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenterImpl;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavDrawerModule.class
})

public interface ApplicationComponent {

    //UniversityApi getUniversityApi();

    NavDrawerPresenter getHeaderPresenter();
}
