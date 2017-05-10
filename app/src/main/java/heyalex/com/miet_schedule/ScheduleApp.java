package heyalex.com.miet_schedule;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


import heyalex.com.miet_schedule.addnewgroup.AddNewGroupComponent;
import heyalex.com.miet_schedule.addnewgroup.AddNewGroupModule;
import heyalex.com.miet_schedule.addnewgroup.DaggerAddNewGroupComponent;
import heyalex.com.miet_schedule.data.DataModule;
import heyalex.com.miet_schedule.groups.DaggerGroupsComponent;
import heyalex.com.miet_schedule.groups.GroupsComponent;
import heyalex.com.miet_schedule.groups.GroupsModule;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.news.DaggerNewsComponent;
import heyalex.com.miet_schedule.news.NewsComponent;
import heyalex.com.miet_schedule.news.NewsModule;
import heyalex.com.miet_schedule.schedule.DaggerScheduleComponent;
import heyalex.com.miet_schedule.schedule.ScheduleComponent;
import heyalex.com.miet_schedule.schedule.ScheduleModule;
import timber.log.Timber;

/**
 * Created by mac on 04.04.17.
 */

public class ScheduleApp extends Application {

    @NonNull // initialized in onCreate()
    private ApplicationComponent applicationComponent;

    private NavDrawerComponent navDrawerComponent;
    private NewsComponent newsComponent;
    private GroupsComponent groupsComponent;
    private AddNewGroupComponent addNewGroupComponent;
    private ScheduleComponent scheduleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        applicationComponent = prepareApplicationComponent().build();
        initDaggerComponents();
        initNewsComponent();
        initGroupsComponent();
        initAddnewGroupComponent();
        initScheduleComponent();
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
                .dataModule(new DataModule(getApplicationContext()));
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initDaggerComponents() {
        this.navDrawerComponent = initNavDrawerComponent();
        this.newsComponent = initNewsComponent();
        this.groupsComponent = initGroupsComponent();
    }

    private NavDrawerComponent initNavDrawerComponent() {
        return DaggerNavDrawerComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    private NewsComponent initNewsComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(applicationComponent)
                .newsModule(new NewsModule())
                .build();
    }

    private GroupsComponent initGroupsComponent() {
        return DaggerGroupsComponent.builder()
                .applicationComponent(applicationComponent)
                .groupsModule(new GroupsModule())
                .build();
    }

    private AddNewGroupComponent initAddnewGroupComponent() {
        return DaggerAddNewGroupComponent.builder()
                .applicationComponent(applicationComponent)
                .addNewGroupModule(new AddNewGroupModule())
                .build();
    }

    private ScheduleComponent initScheduleComponent() {
        return DaggerScheduleComponent.builder()
                .applicationComponent(applicationComponent)
                .scheduleModule(new ScheduleModule())
                .build();
    }


    public NewsComponent getNewsComponent() {
        return newsComponent;
    }

    public GroupsComponent getGroupsComponent() {
        return groupsComponent;
    }

    public AddNewGroupComponent getAddNewGroupComponent() {
        return addNewGroupComponent;
    }


    public ScheduleComponent getScheduleComponent() {
        return scheduleComponent;
    }
}
