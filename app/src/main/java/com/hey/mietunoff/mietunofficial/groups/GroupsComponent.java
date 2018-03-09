package com.hey.mietunoff.mietunofficial.groups;

import com.hey.mietunoff.mietunofficial.schedule_widget.ScheduleAppWidgetConfigureActivity;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */
@GroupsScope
@Component(modules = GroupsModule.class, dependencies = ApplicationComponent.class)
public interface GroupsComponent {
    void inject(GroupsFragment fragment);

    void inject(ScheduleAppWidgetConfigureActivity activity);
}