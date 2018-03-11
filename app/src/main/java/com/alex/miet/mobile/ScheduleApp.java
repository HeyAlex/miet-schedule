package com.alex.miet.mobile;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.alex.miet.mobile.addnewgroup.AddNewGroupComponent;
import com.alex.miet.mobile.addnewgroup.AddNewGroupModule;

import com.alex.miet.mobile.addnewgroup.DaggerAddNewGroupComponent;
import com.alex.miet.mobile.data.DataModule;
import com.alex.miet.mobile.groups.DaggerGroupsComponent;
import com.alex.miet.mobile.groups.GroupsComponent;
import com.alex.miet.mobile.groups.GroupsModule;
import com.alex.miet.mobile.navdrawer.DaggerNavDrawerComponent;
import com.alex.miet.mobile.navdrawer.NavDrawerComponent;
import com.alex.miet.mobile.navdrawer.NavDrawerModule;
import com.alex.miet.mobile.DaggerApplicationComponent;
import com.alex.miet.mobile.news.DaggerNewsComponent;
import com.alex.miet.mobile.news.NewsComponent;
import com.alex.miet.mobile.news.NewsModule;
import com.alex.miet.mobile.schedule.DaggerScheduleComponent;
import com.alex.miet.mobile.schedule.ScheduleComponent;
import com.alex.miet.mobile.schedule.ScheduleModule;
import com.alex.miet.mobile.schedule_builder.ScheduleBuilderModule;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class ScheduleApp extends Application {

    @NonNull
    private ApplicationComponent applicationComponent;

    private NavDrawerComponent navDrawerComponent;
    private NewsComponent newsComponent;
    private GroupsComponent groupsComponent;
    private AddNewGroupComponent addNewGroupComponent;
    private ScheduleComponent scheduleComponent;

    public static ScheduleApp get(Context context) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        return (ScheduleApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        applicationComponent = prepareApplicationComponent().build();
        if (BuildConfig.USE_CRASHLYTICS) {
            Fabric.with(this, new Crashlytics());
        }
        initDaggerComponents();
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
        this.addNewGroupComponent = initAddnewGroupComponent();
        this.scheduleComponent = initScheduleComponent();
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
                .scheduleBuilderModule(new ScheduleBuilderModule())
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
