package heyalex.com.miet_schedule.schedule;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import heyalex.com.miet_schedule.model.schedule.ScheduleComparator;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 12.05.17.
 */

public class ScheduleBuilder {

    public static CycleWeeksLessonModel buildSchedule(List<LessonModel> lessons){
        CycleWeeksLessonModel schedule = new CycleWeeksLessonModel();
        List<DayLessonsModel> weekOne = new ArrayList<>();
        List<DayLessonsModel> weekTwo = new ArrayList<>();
        List<DayLessonsModel> weekThird = new ArrayList<>();
        List<DayLessonsModel> weekFourth = new ArrayList<>();
        DateTime todayDate = new DateTime().now();
        DateTime tommorowDate = todayDate.plusDays(1);
        int weekToday = DateMietHelper.getWeekByDay(todayDate) % 4 - 1;
        int weekTommorow = DateMietHelper.getWeekByDay(tommorowDate) % 4 - 1;
        int dayToday = DateMietHelper.getDayInWeek(todayDate);
        int dayTommorow = DateMietHelper.getDayInWeek(tommorowDate);

        for (int i = 0; i < 7; i++) {
            DayLessonsModel item1 = new DayLessonsModel();
            DayLessonsModel item2 = new DayLessonsModel();
            DayLessonsModel item3 = new DayLessonsModel();
            DayLessonsModel item4 = new DayLessonsModel();
            String week = NavigationUtil.weekDayList[i];

            item1.setDay(week);
            item2.setDay(week);
            item3.setDay(week);
            item4.setDay(week);

            item1.setDayNumber(i);
            item2.setDayNumber(i);
            item3.setDayNumber(i);
            item4.setDayNumber(i);

            List<LessonModel> dataArray1 = new ArrayList<>();
            List<LessonModel> dataArray2 = new ArrayList<>();
            List<LessonModel> dataArray3 = new ArrayList<>();
            List<LessonModel> dataArray4 = new ArrayList<>();

            for (LessonModel data : lessons) {
                if (data.getWeek() == i + 1) {
                    switch (data.getDay()) {
                        case 0: {
                            dataArray1.add(data);
                            break;
                        }
                        case 1: {
                            dataArray2.add(data);
                            break;
                        }
                        case 2: {
                            dataArray3.add(data);
                            break;
                        }
                        case 3: {
                            dataArray4.add(data);
                            break;
                        }
                    }
                }
            }
            if (!dataArray1.isEmpty()) {
                Collections.sort(dataArray1, new ScheduleComparator());
                item1.setLessons(dataArray1);
                weekOne.add(item1);
            }
            if (!dataArray2.isEmpty()) {
                Collections.sort(dataArray2, new ScheduleComparator());
                item2.setLessons(dataArray2);
                weekTwo.add(item2);
            }
            if (!dataArray3.isEmpty()) {
                Collections.sort(dataArray3, new ScheduleComparator());
                item3.setLessons(dataArray3);
                weekThird.add(item3);
            }
            if (!dataArray4.isEmpty()) {
                Collections.sort(dataArray4, new ScheduleComparator());
                item4.setLessons(dataArray4);
                weekFourth.add(item4);
            }

            if(dayToday == i){
                switch (weekToday) {
                    case 1: {
                        schedule.setToday(item1);
                        break;
                    }
                    case 2: {
                        schedule.setToday(item2);
                        break;
                    }
                    case 3: {
                        schedule.setToday(item3);
                        break;
                    }
                    case 4: {
                        schedule.setToday(item4);
                        break;
                    }
                }
            }
            if(dayTommorow == i){
                switch (weekTommorow) {
                    case 1: {
                        schedule.setTommorow(item1);
                        break;
                    }
                    case 2: {
                        schedule.setTommorow(item2);
                        break;
                    }
                    case 3: {
                        schedule.setTommorow(item3);
                        break;
                    }
                    case 4: {
                        schedule.setTommorow(item4);
                        break;
                    }
                }
            }
        }
        schedule.setFirstWeek(weekOne);
        schedule.setSecondWeek(weekTwo);
        schedule.setThirdWeek(weekThird);
        schedule.setFourthWeek(weekFourth);

        return schedule;
    }

    public static DayLessonsModel buildDailySchedule(List<LessonModel> dailyLessons, int day){
        DayLessonsModel dailySchedule = new DayLessonsModel();
        Collections.sort(dailyLessons, new ScheduleComparator());
        dailySchedule.setLessons(dailyLessons);
        dailySchedule.setDayNumber(day);
        dailySchedule.setDay(NavigationUtil.weekDayList[day]);
        return dailySchedule;
    }
}
