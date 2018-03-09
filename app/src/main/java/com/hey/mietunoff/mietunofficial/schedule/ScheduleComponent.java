package com.hey.mietunoff.mietunofficial.schedule;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.ApplicationComponent;
import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilderModule;

/**
 * Created by mac on 10.05.17.
 */

@ScheduleScope
@Component(modules = {ScheduleModule.class, ScheduleBuilderModule.class},
        dependencies = ApplicationComponent.class)
public interface ScheduleComponent {
    void inject(ScheduleActivity activity);

    void inject(ScheduleFragment fragment);
}

