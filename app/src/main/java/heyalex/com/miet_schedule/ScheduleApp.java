package heyalex.com.miet_schedule;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import heyalex.com.miet_schedule.api.UniversityApiModule;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import timber.log.Timber;

/**
 * Created by mac on 04.04.17.
 */

public class ScheduleApp extends Application {

    @NonNull // initialized in onCreate()
    private ApplicationComponent applicationComponent;

    private NavDrawerComponent navDrawerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        applicationComponent = prepareApplicationComponent().build();
        initDaggerComponents();
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
                .universityApiModule(new UniversityApiModule());
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initDaggerComponents() {
        this.navDrawerComponent = initNavDrawerComponent();

    }

    private NavDrawerComponent initNavDrawerComponent() {
        return DaggerNavDrawerComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

}
