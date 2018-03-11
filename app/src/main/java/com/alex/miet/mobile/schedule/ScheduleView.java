package com.alex.miet.mobile.schedule;

import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;

/**
 * An interface for {@link ScheduleActivity}
 */
/*package*/ interface ScheduleView {

    void showSchedule(CycleWeeksLessonModel schedule);

    void showStatus(boolean state);

    void showErrorView();
}
