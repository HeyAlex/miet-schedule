package com.alex.miet.mobile.model.schedule;

import com.alex.miet.mobile.entities.LessonItem;
import com.alex.miet.mobile.schedule_builder.ScheduleBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * One day of student's life
 * Contains all lessons that will be in current day
 * Used in {@link ScheduleBuilder}
 */

public class DayLessonsModel implements Cloneable {

    private String Day;
    private int DayNumber;
    private List<LessonItem> lessons = new ArrayList<>();

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public int getDayNumber() {
        return DayNumber;
    }

    public void setDayNumber(int dayNumber) {
        DayNumber = dayNumber;
    }

    public List<LessonItem> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonItem> lessons) {
        this.lessons = lessons;
    }

    public DayLessonsModel clone() throws CloneNotSupportedException {
        return (DayLessonsModel) super.clone();
    }
}
