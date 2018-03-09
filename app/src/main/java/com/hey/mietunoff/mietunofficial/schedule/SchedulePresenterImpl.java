package com.hey.mietunoff.mietunofficial.schedule;

import com.hey.mietunoff.mietunofficial.data.shared_interactor.OnScheduleDownload;
import com.hey.mietunoff.mietunofficial.data.shared_interactor.ScheduleInteractor;
import com.hey.mietunoff.mietunofficial.model.schedule.CycleWeeksLessonModel;
import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilder;

import java.util.List;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import io.reactivex.disposables.CompositeDisposable;

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
        CycleWeeksLessonModel schedule = scheduleInteractor.getCacheGroup(groupName);
        if (view != null) {
            view.showSchedule(schedule);
            view.showStatus(false);
        }
    }

    @Override
    public void updateScheduleForGroup(String groupName) {
        view.showStatus(true);
        scheduleInteractor.downloadGroup(groupName);
    }

    @Override
    public void onGroupDownloaded(List<LessonModel> lessons, String groupName) {
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

