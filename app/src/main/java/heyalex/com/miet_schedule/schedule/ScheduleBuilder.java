package heyalex.com.miet_schedule.schedule;

import java.util.List;
import java.util.concurrent.Callable;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 12.05.17.
 */

public class ScheduleBuilder {
    public static CycleWeeksLessonModel buildSchedule(List<LessonModel> lessons){
        CycleWeeksLessonModel schedule = new CycleWeeksLessonModel();


        return schedule;
    }


}
