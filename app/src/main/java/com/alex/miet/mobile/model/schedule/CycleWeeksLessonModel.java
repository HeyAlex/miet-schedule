package com.alex.miet.mobile.model.schedule;

import com.alex.miet.mobile.schedule_builder.ScheduleBuilder;

import java.util.List;

/**
 * That class is holder of full schedule for a group.
 * Used in {@link ScheduleBuilder} for build a schedule
 * module
 */
public class CycleWeeksLessonModel {

    /**
     * Day that will contains all lesson for today
     */
    private DayLessonsModel today;

    /**
     * Day that will contains all lesson for tommorow
     */
    private DayLessonsModel tomorrow;

    /**
     * Schedule for first numerator
     */
    private List<DayLessonsModel> firstWeek;

    /**
     * Schedule for first denumerator
     */
    private List<DayLessonsModel> secondWeek;

    /**
     * Schedule for second numerator
     */
    private List<DayLessonsModel> thirdWeek;

    /**
     * Schedule for second denumerator
     */
    private List<DayLessonsModel> fourthWeek;

    public List<DayLessonsModel> getFirstWeek() {
        return firstWeek;
    }

    public void setFirstWeek(List<DayLessonsModel> firstWeek) {
        this.firstWeek = firstWeek;
    }

    public List<DayLessonsModel> getSecondWeek() {
        return secondWeek;
    }

    public void setSecondWeek(List<DayLessonsModel> secondWeek) {
        this.secondWeek = secondWeek;
    }

    public List<DayLessonsModel> getThirdWeek() {
        return thirdWeek;
    }

    public void setThirdWeek(List<DayLessonsModel> thirdWeek) {
        this.thirdWeek = thirdWeek;
    }

    public List<DayLessonsModel> getFourthWeek() {
        return fourthWeek;
    }

    public void setFourthWeek(List<DayLessonsModel> fourthWeek) {
        this.fourthWeek = fourthWeek;
    }

    public DayLessonsModel getToday() {
        return today;
    }

    public void setToday(DayLessonsModel today) {
        this.today = today;
    }

    public DayLessonsModel getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(DayLessonsModel tomorrow) {
        this.tomorrow = tomorrow;
    }
}
