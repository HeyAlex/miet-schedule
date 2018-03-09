package com.hey.mietunoff.mietunofficial.model.schedule;

import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilder;

import java.util.ArrayList;
import java.util.List;

import com.hey.mietunoff.mietunofficial.LessonModel;

/**
 * One day of student's life
 * Contains all lessons that will be in current day
 * Used in {@link ScheduleBuilder}
 */

public class DayLessonsModel implements Cloneable {

    private String Day;
    private int DayNumber;
    private List<LessonModel> lessons = new ArrayList<>();

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

    public List<LessonModel> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonModel> lessons) {
        this.lessons = lessons;
    }

    public DayLessonsModel clone() throws CloneNotSupportedException {
        return (DayLessonsModel) super.clone();
    }
}
