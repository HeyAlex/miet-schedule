package com.alex.miet.mobile.groups;

import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

import java.util.List;

import com.alex.miet.mobile.entities.GroupItem;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

/*package*/ class GroupsPresenterImpl implements GroupsPresenter {

    private GroupsView view;
    private ScheduleInteractor interactor;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /*package*/ GroupsPresenterImpl(ScheduleInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void showGroups() {
        mCompositeDisposable.add(
                interactor.getDownloadedGroups().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableMaybeObserver<List<GroupItem>>() {

                    @Override
                    public void onSuccess(List<GroupItem> groups) {
                        if (view != null) {
                            if (groups != null) {
                                if (!groups.isEmpty()) {
                                    view.showGroups(groups);
                                } else {
                                    view.showHint();
                                }
                            } else {
                                view.showHint();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void deleteGroup(String groupName) {
        interactor.deleteGroup(groupName);
    }

    @Override
    public void addNewStaticShortcut(String groupName) {
        interactor.addNewStaticShortcut(groupName);
    }

    @Override
    public void requestWidget(String groupName) {
        interactor.requestWidgetOnHomescreen(groupName);
    }

    @Override
    public void onViewAttached(GroupsView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
