package heyalex.com.miet_schedule.schedule;

import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;

/**
 * An interface for {@link ScheduleActivity}
 */
/*package*/ interface ScheduleView {

    void showSchedule(CycleWeeksLessonModel schedule);

    void showStatus(boolean state);

    void showErrorView();
}
