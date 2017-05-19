package heyalex.com.miet_schedule.schedule;

import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;

/**
 * Created by mac on 10.05.17.
 */

/*package*/ interface ScheduleView {

    void showSchedule(CycleWeeksLessonModel schedule);

    void showStatus(boolean state);

    void showReloadedSchedule(CycleWeeksLessonModel schedule);

    void showErrorView();
}
