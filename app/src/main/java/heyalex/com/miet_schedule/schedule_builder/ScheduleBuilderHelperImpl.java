package heyalex.com.miet_schedule.schedule_builder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import timber.log.Timber;

/**
 * Created by alexf on 14.05.2017.
 */

public class ScheduleBuilderHelperImpl implements ScheduleBuilderHelper{

    private CycleWeeksLessonModel schedule = null;
    private LessonsRepository lessonsRepository;

    @Inject
    public ScheduleBuilderHelperImpl(LessonsRepository lessonsRepository){
        this.lessonsRepository = lessonsRepository;
        Timber.i("new object ScheduleBuilderHelperImpl");
    }


    @Override
    public List<DayLessonsModel> getLessonsForCurrentFragment(int position) {
        if(schedule != null){
            switch (position){
                case 0:{
                    final List<DayLessonsModel> todayLessons = new ArrayList<>();
                    if(schedule.getToday() != null){
                        todayLessons.add(schedule.getToday());
                    }else {
                        return null;
                    }
                    return todayLessons;
                }
                case 1:{
                    final List<DayLessonsModel> tommorowLessons = new ArrayList<>();
                    if(schedule.getTommorow() != null){
                        tommorowLessons.add(schedule.getTommorow());
                    }else {
                        return null;
                    }
                    return tommorowLessons;
                }
                case 2:{
                    return schedule.getFirstWeek();
                }
                case 3:{
                    return schedule.getSecondWeek();
                }
                case 4:{
                    return schedule.getThirdWeek();
                }
                case 5:{
                    return schedule.getFourthWeek();
                }
            }
        }
        Timber.i("ScheduleBuilderHelperImpl is null");
        return null;
    }

    @Override
    public void setBuildedLessonSchedule(CycleWeeksLessonModel schedule) {
        Timber.i("ScheduleBuilderHelperImpl set a builded schedule");
        this.schedule = schedule;
    }

    @Override
    public boolean isEmptySchedule() {
        return schedule == null;
    }
}
