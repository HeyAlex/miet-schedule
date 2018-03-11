package com.alex.miet.mobile.groups;

import com.alex.miet.mobile.schedule_widget.ScheduleAppWidgetConfigureActivity;

import dagger.Component;
import com.alex.miet.mobile.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */
@GroupsScope
@Component(modules = GroupsModule.class, dependencies = ApplicationComponent.class)
public interface GroupsComponent {
    void inject(GroupsFragment fragment);

    void inject(ScheduleAppWidgetConfigureActivity activity);
}