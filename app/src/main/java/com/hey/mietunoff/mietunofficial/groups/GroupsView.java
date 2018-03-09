package com.hey.mietunoff.mietunofficial.groups;

import com.hey.mietunoff.mietunofficial.schedule_widget.ScheduleAppWidgetConfigureActivity;

import java.util.List;

import com.hey.mietunoff.mietunofficial.ScheduleModel;

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
    void showGroups(List<ScheduleModel> groups);
}
