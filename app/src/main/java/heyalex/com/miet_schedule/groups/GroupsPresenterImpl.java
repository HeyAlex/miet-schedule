package heyalex.com.miet_schedule.groups;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.news.NewsView;

/**
 * Created by mac on 09.05.17.
 */

/*package*/ class GroupsPresenterImpl implements GroupsPresenter{

    private GroupsView view;
    private ScheduleRepository groupsRepository;


    /*package*/ GroupsPresenterImpl(ScheduleRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public void showGroups() {
        final List<ScheduleModel> groups = groupsRepository.getAll();
        if(!groups.isEmpty()){
            view.showGroups(groups);
        }else {
            view.showHint();
        }
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
