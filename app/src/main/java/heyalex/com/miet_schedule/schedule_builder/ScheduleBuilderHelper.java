package heyalex.com.miet_schedule.schedule_builder;

import java.util.List;

import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;

/**
 * Created by alexf on 14.05.2017.
 */

public interface ScheduleBuilderHelper {
    List<DayLessonsModel> getLessonsForCurrentFragment(int position);

    void setBuildedLessonSchedule(CycleWeeksLessonModel schedule);

    boolean isEmptySchedule();
}
