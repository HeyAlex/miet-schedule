package com.alex.miet.mobile.schedule;

import com.alex.miet.mobile.data.shared_interactor.OnScheduleDownload;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;
import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;
import com.alex.miet.mobile.schedule_builder.ScheduleBuilder;

import java.util.List;

import javax.inject.Inject;

import com.alex.miet.mobile.entities.LessonItem;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Specific {@link SchedulePresenter} implementation
 */
/*package*/ class SchedulePresenterImpl implements SchedulePresenter, OnScheduleDownload {

    private ScheduleInteractor scheduleInteractor;
    private ScheduleView view;
    private CompositeDisposable scheduleCompositeDisposable;

    @Inject
    /*package*/ SchedulePresenterImpl(ScheduleInteractor scheduleInteractor) {
        this.scheduleInteractor = scheduleInteractor;
    }

    @Override
    public void onViewAttached(ScheduleView view) {
        this.view = view;
        scheduleInteractor.attach(this);
        scheduleCompositeDisposable = new CompositeDisposable();
        view.showStatus(scheduleCompositeDisposable.size() != 0);
    }

    @Override
    public void onViewDetached() {
        this.view = null;
        scheduleInteractor.detach();
        scheduleCompositeDisposable.dispose();
    }

    @Override
    public void getCachedScheduleForGroup(String groupName) {
        scheduleCompositeDisposable.add(
                scheduleInteractor.getCacheGroup(groupName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableMaybeObserver<CycleWeeksLessonModel>(){

                    @Override
                    public void onSuccess(CycleWeeksLessonModel groupItem) {
                        if (view != null && groupItem != null) {
                            view.showSchedule(groupItem);
                            view.showStatus(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    public void updateScheduleForGroup(String groupName) {
        view.showStatus(true);
        scheduleInteractor.downloadGroup(groupName);
    }

    @Override
    public void onGroupDownloaded(List<LessonItem> lessons, String groupName) {
        if (view != null) {
            try {
                view.showSchedule(ScheduleBuilder.buildSchedule(lessons));
            } catch (CloneNotSupportedException e) {
                //never happens
            }
            view.showStatus(false);
        }
    }

    @Override
    public void onErrorWhileDownloadingGroup(String groupName) {
        if (view != null) {
            view.showStatus(false);
            view.showErrorView();
        }
    }
}

