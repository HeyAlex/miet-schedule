package heyalex.com.miet_schedule.schedule;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.schedule_builder.ScheduleBuilder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mac on 10.05.17.
 */

public class SchedulePresenterImpl implements SchedulePresenter{

    private ScheduleRepository scheduleRepository;
    private LessonsRepository lessonsRepository;
    private ScheduleView view;
    private final CompositeDisposable scheduleCompositeDisposable = new CompositeDisposable();

    @Inject
    public SchedulePresenterImpl(ScheduleRepository scheduleRepository,
                                 LessonsRepository lessonsRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonsRepository = lessonsRepository;
        Timber.i("new object SchedulePresenterImpl");
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
        scheduleCompositeDisposable.add(retrieveSchedule(groupName)
                .subscribeWith(new ResponseNewsSubscriber()));
    }


    public Observable<CycleWeeksLessonModel> retrieveSchedule(final String groupName) {
        return Observable.fromCallable(new Callable<ScheduleModel>() {
                @Override
                public ScheduleModel call() throws Exception {
                    return scheduleRepository.getGroupByName(groupName);
                }
            }).map(new Function<ScheduleModel, CycleWeeksLessonModel>() {
                @Override
                 public CycleWeeksLessonModel apply(ScheduleModel scheduleModel) throws Exception {
                    return ScheduleBuilder.buildSchedule(scheduleModel.getLessons());
                }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
    }

    private class ResponseNewsSubscriber extends DisposableObserver<CycleWeeksLessonModel> {

        public ResponseNewsSubscriber() {
        }

        @Override
        public void onNext(CycleWeeksLessonModel schedule) {
            //CycleWeeksLessonModel s = schedule;
            view.showSchedule(schedule);
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }
}

