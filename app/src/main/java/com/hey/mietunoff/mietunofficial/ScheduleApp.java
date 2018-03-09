package com.hey.mietunoff.mietunofficial;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hey.mietunoff.mietunofficial.addnewgroup.AddNewGroupComponent;
import com.hey.mietunoff.mietunofficial.addnewgroup.AddNewGroupModule;

import com.hey.mietunoff.mietunofficial.addnewgroup.DaggerAddNewGroupComponent;
import com.hey.mietunoff.mietunofficial.data.DataModule;
import com.hey.mietunoff.mietunofficial.groups.DaggerGroupsComponent;
import com.hey.mietunoff.mietunofficial.groups.GroupsComponent;
import com.hey.mietunoff.mietunofficial.groups.GroupsModule;
import com.hey.mietunoff.mietunofficial.navdrawer.DaggerNavDrawerComponent;
import com.hey.mietunoff.mietunofficial.navdrawer.NavDrawerComponent;
import com.hey.mietunoff.mietunofficial.navdrawer.NavDrawerModule;
import com.hey.mietunoff.mietunofficial.news.DaggerNewsComponent;
import com.hey.mietunoff.mietunofficial.news.NewsComponent;
import com.hey.mietunoff.mietunofficial.news.NewsModule;
import com.hey.mietunoff.mietunofficial.schedule.DaggerScheduleComponent;
import com.hey.mietunoff.mietunofficial.schedule.ScheduleComponent;
import com.hey.mietunoff.mietunofficial.schedule.ScheduleModule;
import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilderModule;
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
