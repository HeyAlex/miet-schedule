package heyalex.com.miet_schedule.schedule;

import heyalex.com.miet_schedule.util.BasePresenter;

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
