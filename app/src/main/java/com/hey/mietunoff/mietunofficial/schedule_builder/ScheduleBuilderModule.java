package com.hey.mietunoff.mietunofficial.schedule_builder;

import com.hey.mietunoff.mietunofficial.schedule.ScheduleScope;

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