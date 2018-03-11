package com.alex.miet.mobile.schedule_builder;

import com.alex.miet.mobile.schedule.ScheduleScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScheduleBuilderModule {

    @Provides
    @ScheduleScope
    /*package*/ ScheduleBuilderHelper provideScheduleBuilder() {
        return new ScheduleBuilderHelperImpl();
    }
}