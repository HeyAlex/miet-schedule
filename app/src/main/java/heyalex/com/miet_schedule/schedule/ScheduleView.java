package heyalex.com.miet_schedule.schedule;

import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.mvp.BaseView;

/**
 * Created by mac on 10.05.17.
 */

public interface ScheduleView{
    void showSchedule(CycleWeeksLessonModel schedule);
}
