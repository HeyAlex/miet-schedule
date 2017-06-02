package heyalex.com.miet_schedule.groups;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.shortcut.ShortcutPreference;

/**
 * Created by mac on 09.05.17.
 */

/*package*/ class GroupsPresenterImpl implements GroupsPresenter {

    private GroupsView view;
    private ScheduleRepository groupsRepository;
    private ShortcutPreference shortcutPreference;


    /*package*/ GroupsPresenterImpl(ScheduleRepository groupsRepository,
                                    ShortcutPreference shortcutPreference) {
        this.groupsRepository = groupsRepository;
        this.shortcutPreference = shortcutPreference;
    }

    @Override
    public void showGroups() {
        final List<ScheduleModel> groups = groupsRepository.getAll();

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

    }

    @Override
    public void addNewStaticShortcut(String groupName) {

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
