package heyalex.com.miet_schedule.groups;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractor;

/*package*/ class GroupsPresenterImpl implements GroupsPresenter {

    private GroupsView view;
    private ScheduleInteractor interactor;


    /*package*/ GroupsPresenterImpl(ScheduleInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void showGroups() {
        final List<ScheduleModel> groups = interactor.getDownloadedGroups();

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
