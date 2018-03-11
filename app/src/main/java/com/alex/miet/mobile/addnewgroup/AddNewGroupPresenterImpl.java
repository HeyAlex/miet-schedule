package com.alex.miet.mobile.addnewgroup;

import java.util.List;

import javax.inject.Inject;

import com.alex.miet.mobile.LessonModel;
import com.alex.miet.mobile.api.UniversityApiFactory;
import com.alex.miet.mobile.data.shared_interactor.OnScheduleDownload;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;
import com.alex.miet.mobile.search.DataFilter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AddNewGroupPresenterImpl implements AddNewGroupPresenter, OnScheduleDownload {

    private final CompositeDisposable scheduleResponseSubscription = new CompositeDisposable();
    private AddNewGroupView view;
    private String searchQuery = "";
    private List<String> cachedGroups;
    private DataFilter<String> groupFilter = new GroupsFilter();
    private ScheduleInteractor interactor;

    @Inject
    public AddNewGroupPresenterImpl(ScheduleInteractor interactor) {
        this.interactor = interactor;
    }


    private void getAvailableGroups() {
        view.showDownloadingAvailibleGroups();
        scheduleResponseSubscription.add(UniversityApiFactory.getUniversityApi().getGroupNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseAvailableGroups()));
    }

    @Override
    public void addNewGroup(String groupName) {
        view.showDownloadingGroup(groupName);
        interactor.downloadGroup(groupName);
    }

    @Override
    public void onSearch(String searchQuery) {
        this.searchQuery = searchQuery;
        showGroups();
    }

    @Override
    public void onSearchCanceled() {
        searchQuery = "";
        showGroups();
    }

    @Override
    public void onCancelDownloadingGroup() {
        interactor.stopDownloading();
    }

    private boolean isSearching() {
        return searchQuery != null && !searchQuery.isEmpty();
    }

    @Override
    public void showGroups() {
        if (cachedGroups != null) {
            if (isSearching()) {
                view.showAvailibleGroups(groupFilter.filter(cachedGroups, searchQuery));
            } else {
                view.showAvailibleGroups(cachedGroups);
            }
        } else {
            getAvailableGroups();
        }
    }

    @Override
    public void onViewAttached(AddNewGroupView view) {
        this.view = view;
        interactor.attach(this);
        showGroups();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
        interactor.detach();
        scheduleResponseSubscription.dispose();
    }

    @Override
    public void onGroupDownloaded(List<LessonModel> lessons, String groupName) {
        if (view != null) {
            view.hideDownloading();
            if (view != null) {
                view.finishPickingGroups();
            }
        }
    }

    @Override
    public void onErrorWhileDownloadingGroup(String groupName) {
        view.showErrorView("");
        view.hideDownloading();
    }

    private class ResponseAvailableGroups extends DisposableSingleObserver<List<String>> {

        @Override
        public void onSuccess(List<String> value) {
            Timber.i("Set of groups have successfully received.");
            if (view != null) {
                cachedGroups = value;
                view.showAvailibleGroups(value);
                view.hideDownloading();
            }
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e, "An error occurred while trying to take groups");
            if (view != null) {
                view.hideDownloading();
                view.showErrorView("при скачивании доступных групп.");
            }
        }
    }
}
