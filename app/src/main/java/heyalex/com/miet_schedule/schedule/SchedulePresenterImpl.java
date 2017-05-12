package heyalex.com.miet_schedule.schedule;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 10.05.17.
 */

public class SchedulePresenterImpl implements SchedulePresenter{

    private ScheduleRepository scheduleRepository;
    private LessonsRepository lessonsRepository;
    private ScheduleView view;

    @Inject
    public SchedulePresenterImpl(ScheduleRepository scheduleRepository,
                                 LessonsRepository lessonsRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void onViewAttached(ScheduleView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void getCachedScheduleForGroup(String groupName) {
        final ScheduleModel model =

    }

    public static Observable<List<DayLessonsModel>> retrieveSchedule(final String group, final long from) {
        return Observable.fromCallable(new Callable<List<LessonModel>>() {
            @Override
            public List<LessonModel> call() {
                return scheduleRepository.getGroupByName(group);
            }
        })
                .map(new Func1<List<Lesson>, List<DayLessonsModel>>() {
                    @Override
                    public List<DayLessonsModel> call(List<Lesson> lessons) {
                        return ScheduleHelper.buildDailySchedule(lessons, SEPARATOR);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
