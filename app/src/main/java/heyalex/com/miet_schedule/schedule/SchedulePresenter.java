package heyalex.com.miet_schedule.schedule;

import java.util.List;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.mvp.BasePresenter;

/**
 * Created by alexf on 07.04.2017.
 */

public interface SchedulePresenter extends BasePresenter<ScheduleView>{

    void getCachedScheduleForGroup(String groupName);

    void updateScheduleForGroup(String groupName);
}
