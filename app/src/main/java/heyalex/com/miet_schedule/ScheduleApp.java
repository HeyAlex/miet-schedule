package heyalex.com.miet_schedule;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import heyalex.com.miet_schedule.api.UniversityApiModule;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.news.DaggerNewsComponent;
import heyalex.com.miet_schedule.news.NewsComponent;
import heyalex.com.miet_schedule.news.NewsModule;
import timber.log.Timber;

/**
 * Created by mac on 04.04.17.
 */

public class ScheduleApp extends Application {

    @NonNull // initialized in onCreate()
    private ApplicationComponent applicationComponent;

    private NavDrawerComponent navDrawerComponent;
    private NewsComponent newsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        applicationComponent = prepareApplicationComponent().build();
        initDaggerComponents();
        initNewsComponent();
    }

    /**
     * Static method returns {@link ScheduleApp} instance from context
     *
     * @param context to get {@link ScheduleApp}
     * @return {@link ScheduleApp} instance
     */
    public static ScheduleApp get(Context context) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        return (ScheduleApp) context.getApplicationContext();
    }

    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .navDrawerModule(new NavDrawerModule())
                .newsModule(new NewsModule())
                .universityApiModule(new UniversityApiModule());
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initDaggerComponents() {
        this.navDrawerComponent = initNavDrawerComponent();
        this.newsComponent = initNewsComponent();

    }

    private NavDrawerComponent initNavDrawerComponent() {
        return DaggerNavDrawerComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    private NewsComponent initNewsComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

}
