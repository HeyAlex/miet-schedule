package heyalex.com.miet_schedule.schedule;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.addnewgroup.AddNewGroupPresenterImpl;
import heyalex.com.miet_schedule.api.UniversityApiFactory;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.model.schedule.SemesterData;
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

/*package*/ class SchedulePresenterImpl implements SchedulePresenter {

    private ScheduleRepository scheduleRepository;
    private LessonsRepository lessonsRepository;
    private ScheduleView view;
    private final CompositeDisposable scheduleCompositeDisposable = new CompositeDisposable();

    @Inject
    /*package*/ SchedulePresenterImpl(ScheduleRepository scheduleRepository,
                                      LessonsRepository lessonsRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void onViewAttached(ScheduleView view) {
        this.view = view;
        view.showStatus(scheduleCompositeDisposable.size() == 0);
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

    @Override
    public void updateScheduleForGroup(String groupName) {
        view.showStatus(true);
        scheduleCompositeDisposable.add(UniversityApiFactory.getUniversityApi()
                .getScheduleResponse(groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseScheduleObserver(groupName)));
    }


    private class ResponseScheduleObserver extends DisposableObserver<SemesterData> {

        private String groupName;

        /*package*/ ResponseScheduleObserver(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public void onNext(SemesterData semestrResponse) {
            Timber.i("Schedule for '%s' have successfully recived.", groupName);
            List<LessonModel> lessons = AddNewGroupPresenterImpl.
                    transformToDaoLessonModel(semestrResponse, groupName);
            lessonsRepository.replaceAllByGroupName(groupName, lessons);
            scheduleRepository.replaceByGroupName(groupName,
                    AddNewGroupPresenterImpl.transformToDaoScheduleModel(semestrResponse, groupName));
            if (view != null) {
                try {
                    view.showReloadedSchedule(ScheduleBuilder.buildSchedule(lessons));
                } catch (CloneNotSupportedException e) {
                    //never happens
                }
                view.showStatus(false);
            }
            scheduleCompositeDisposable.clear();
        }

        @Override
        public void onError(Throwable t) {
            Timber.e(t, "An error occurred while trying to take shedule for group '%s,", groupName);
            scheduleCompositeDisposable.clear();
            if (view != null) {
                view.showStatus(false);
                view.showErrorView();
            }
            scheduleCompositeDisposable.clear();
        }

        @Override
        public void onComplete() {
        }
    }

    private Observable<CycleWeeksLessonModel> retrieveSchedule(final String groupName) {
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

        /*package*/ ResponseNewsSubscriber() {
        }

        @Override
        public void onNext(CycleWeeksLessonModel schedule) {
            if (view != null) {
                view.showSchedule(schedule);
            }
            scheduleCompositeDisposable.clear();
        }

        @Override
        public void onError(Throwable t) {
            scheduleCompositeDisposable.clear();
        }

        @Override
        public void onComplete() {

        }
    }
}

