package com.alex.miet.mobile.groups;

import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

import java.util.List;

import com.alex.miet.mobile.entities.GroupItem;

/*package*/ class GroupsPresenterImpl implements GroupsPresenter {

    private GroupsView view;
    private ScheduleInteractor interactor;


    /*package*/ GroupsPresenterImpl(ScheduleInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void showGroups() {
        final List<GroupItem> groups = interactor.getDownloadedGroups();

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
