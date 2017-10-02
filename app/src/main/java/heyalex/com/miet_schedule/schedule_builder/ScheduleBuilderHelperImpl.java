package heyalex.com.miet_schedule.schedule_builder;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
import timber.log.Timber;

/*package*/ class ScheduleBuilderHelperImpl implements ScheduleBuilderHelper {

    private CycleWeeksLessonModel schedule = null;
    private DateTime today;
    private final String datePattern = "d MMMM yyyy";

    @Inject
    /*package*/ ScheduleBuilderHelperImpl() {
    }

    @Override
    public List<DayLessonsModel> getLessonsForCurrentFragment(int position) {
        if (schedule != null) {
            switch (position) {
                case 0: {
                    final List<DayLessonsModel> todayLessons = new ArrayList<>();
                    if (schedule.getToday() != null) {
                        schedule.getToday().setDay(toStringScheduleDay(today,
                                DateMietHelper.getWeek(today)));
                    } else {
                        schedule.setToday(new DayLessonsModel());
                    }

                    todayLessons.add(schedule.getToday());
                    return todayLessons;
                }
                case 1: {
                    final List<DayLessonsModel> tommorowLessons = new ArrayList<>();
                    if (schedule.getTomorrow() != null) {
                        schedule.getTomorrow().setDay(toStringScheduleDay(today.plusDays(1),
                                DateMietHelper.getWeek(today.plusDays(1))));
                    } else {
                        schedule.setTomorrow(new DayLessonsModel());
                    }

                    tommorowLessons.add(schedule.getTomorrow());
                    return tommorowLessons;
                }
                case 2: {
                    return schedule.getFirstWeek();
                }
                case 3: {
                    return schedule.getSecondWeek();
                }
                case 4: {
                    return schedule.getThirdWeek();
                }
                case 5: {
                    return schedule.getFourthWeek();
                }
            }
        }
        Timber.i("Schedule isn't built");
        return null;
    }

    private String toStringScheduleDay(DateTime date, int week) {
        return date.toString(datePattern, new Locale("ru")) + "г. ("
                + NavigationUtil.weekListLong[week + 2] + ")";
    }

    @Override
    public void setBuildedLessonSchedule(CycleWeeksLessonModel schedule) {
        Timber.i("ScheduleBuilderHelperImpl set a built schedule");
        this.today = DateTime.now();
        this.schedule = schedule;
    }

    @Override
    public boolean isEmptySchedule() {
        return schedule == null;
    }
}
