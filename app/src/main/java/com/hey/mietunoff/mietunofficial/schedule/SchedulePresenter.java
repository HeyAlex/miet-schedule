package com.hey.mietunoff.mietunofficial.schedule;

import com.hey.mietunoff.mietunofficial.util.BasePresenter;

/**
 * Presenter for {@link ScheduleView}
 */
/*package*/ interface SchedulePresenter extends BasePresenter<ScheduleView> {

    /**
     * Get schedule for specific group
     *
     * @param groupName associated with schedule
     */
    void getCachedScheduleForGroup(String groupName);

    /**
     * Download and update schedule for specific group
     *
     * @param groupName associated with schedule
     */
    void updateScheduleForGroup(String groupName);
}
