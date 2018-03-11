package com.alex.miet.mobile.model.schedule;

import java.util.Comparator;

import com.alex.miet.mobile.LessonModel;

/**
 * Comparator for {@link LessonModel}
 */

public class ScheduleComparator implements Comparator<LessonModel> {

    @Override
    public int compare(LessonModel data, LessonModel t1) {
        return data.getCode().compareTo(t1.getCode());
    }
}