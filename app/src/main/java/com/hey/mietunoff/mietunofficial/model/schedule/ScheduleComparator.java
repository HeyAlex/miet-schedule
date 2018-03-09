package com.hey.mietunoff.mietunofficial.model.schedule;

import java.util.Comparator;

import heyalex.com.miet_schedule.LessonModel;

/**
 * Comparator for {@link LessonModel}
 */

public class ScheduleComparator implements Comparator<LessonModel> {

    @Override
    public int compare(LessonModel data, LessonModel t1) {
        return data.getCode().compareTo(t1.getCode());
    }
}