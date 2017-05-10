package heyalex.com.miet_schedule.model.schedule;

import java.util.List;

/**
 * Created by alexf on 10.05.2017.
 */

public class CycleWeeksLessonModel {
    private DayLessonsModel today;
    private DayLessonsModel tommorow;
    private List<DayLessonsModel> firstWeek;
    private List<DayLessonsModel> secondWeek;
    private List<DayLessonsModel> thirdWeek;
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

    public DayLessonsModel getTommorow() {
        return tommorow;
    }

    public void setTommorow(DayLessonsModel tommorow) {
        this.tommorow = tommorow;
    }
}
