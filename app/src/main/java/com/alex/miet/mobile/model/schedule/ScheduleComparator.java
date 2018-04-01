package com.alex.miet.mobile.model.schedule;

import java.util.Comparator;

import com.alex.miet.mobile.entities.LessonItem;
import com.alex.miet.mobile.entities.LessonItem;

/**
 * Comparator for {@link LessonItem}
 */

public class ScheduleComparator implements Comparator<LessonItem> {

    @Override
    public int compare(LessonItem data, LessonItem t1) {
        return data.getCode().compareTo(t1.getCode());
    }
}