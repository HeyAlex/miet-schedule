package heyalex.com.miet_schedule.navdrawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 11.03.17.
 */

@Module
public class NavDrawerModule {

    @Singleton
    @Provides
    public NavDrawerPresenter provideNavDrawerPresenter() {
        return new NavDrawerPresenterImpl();
    }
}

