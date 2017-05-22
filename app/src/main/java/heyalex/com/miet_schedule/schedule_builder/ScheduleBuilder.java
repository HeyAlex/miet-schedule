package heyalex.com.miet_schedule.schedule_builder;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import heyalex.com.miet_schedule.model.schedule.ScheduleComparator;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;

/**
 * Created by mac on 12.05.17.
 */

public class ScheduleBuilder {

    public static CycleWeeksLessonModel buildSchedule(List<LessonModel> lessons)
            throws CloneNotSupportedException {
        CycleWeeksLessonModel schedule = new CycleWeeksLessonModel();
        List<DayLessonsModel> weekOne = new ArrayList<>();
        List<DayLessonsModel> weekTwo = new ArrayList<>();
        List<DayLessonsModel> weekThird = new ArrayList<>();
        List<DayLessonsModel> weekFourth = new ArrayList<>();
        DateTime todayDate = new DateTime();
        DateTime tommorowDate = todayDate.plusDays(1);
        int weekToday = DateMietHelper.getWeek(todayDate);
        int weekTommorow = DateMietHelper.getWeek(tommorowDate);
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
                if (data.getDay() == i + 1) {
                    switch (data.getWeek()) {
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

            if (dayToday - 1 == i) {
                switch (weekToday) {
                    case 0: {
                        schedule.setToday(item1.clone());
                        break;
                    }
                    case 1: {
                        schedule.setToday(item2.clone());
                        break;
                    }
                    case 2: {
                        schedule.setToday(item3.clone());
                        break;
                    }
                    case 3: {
                        schedule.setToday(item4.clone());
                        break;
                    }
                }
            }
            if (dayTommorow - 1 == i) {
                switch (weekTommorow) {
                    case 0: {
                        schedule.setTommorow(item1.clone());
                        break;
                    }
                    case 1: {
                        schedule.setTommorow(item2.clone());
                        break;
                    }
                    case 2: {
                        schedule.setTommorow(item3.clone());
                        break;
                    }
                    case 3: {
                        schedule.setTommorow(item4.clone());
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

    public static DayLessonsModel buildDailySchedule(List<LessonModel> dailyLessons, int day) {
        DayLessonsModel dailySchedule = new DayLessonsModel();
        Collections.sort(dailyLessons, new ScheduleComparator());
        dailySchedule.setLessons(dailyLessons);
        dailySchedule.setDayNumber(day);
        dailySchedule.setDay(NavigationUtil.weekDayList[day]);
        return dailySchedule;
    }
}
