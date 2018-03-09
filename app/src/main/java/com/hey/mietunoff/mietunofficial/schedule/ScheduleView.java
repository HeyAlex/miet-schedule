package com.hey.mietunoff.mietunofficial.schedule;

import com.hey.mietunoff.mietunofficial.model.schedule.CycleWeeksLessonModel;

/**
 * An interface for {@link ScheduleActivity}
 */
/*package*/ interface ScheduleView {

    void showSchedule(CycleWeeksLessonModel schedule);

    void showStatus(boolean state);

    void showErrorView();
}
