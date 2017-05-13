package heyalex.com.miet_schedule.model.schedule;

import java.util.Comparator;

import heyalex.com.miet_schedule.LessonModel;

/**
 * Created by alexf on 13.05.2017.
 */

public class ScheduleComparator implements Comparator<LessonModel> {

    @Override
    public int compare(LessonModel data, LessonModel t1) {
        return data.getCode().compareTo(t1.getCode());
    }
}