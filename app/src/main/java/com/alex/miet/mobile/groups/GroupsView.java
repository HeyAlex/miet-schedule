package com.alex.miet.mobile.groups;

import com.alex.miet.mobile.schedule_widget.ScheduleAppWidgetConfigureActivity;

import java.util.List;

import com.alex.miet.mobile.entities.GroupItem;

/**
 * An interface for {@link GroupsFragment} and
 * {@link ScheduleAppWidgetConfigureActivity}
 */
public interface GroupsView {

    /**
     * Show hint if no groups added by user
     */
    void showHint();

    /**
     * Show groups
     *
     * @param groups that user already downloaded
     */
    void showGroups(List<GroupItem> groups);
}
