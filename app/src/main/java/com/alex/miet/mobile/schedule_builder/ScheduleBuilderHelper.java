package com.alex.miet.mobile.schedule_builder;

import java.util.List;

import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;
import com.alex.miet.mobile.model.schedule.DayLessonsModel;

/**
 * An interface for {@link ScheduleBuilderHelperImpl}
 */
public interface ScheduleBuilderHelper {

    /**
     * Get schedule for current tab of viewpager
     *
     * @param position of viewpager
     * @return list with schedule for current tab
     */
    List<DayLessonsModel> getLessonsForCurrentFragment(int position);

    /**
     * set schedule for current group
     *
     * @param schedule built POJO
     */
    void setBuildedLessonSchedule(CycleWeeksLessonModel schedule);

    boolean isEmptySchedule();
}
