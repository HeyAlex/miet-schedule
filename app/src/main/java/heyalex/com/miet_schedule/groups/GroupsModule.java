package heyalex.com.miet_schedule.groups;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class GroupsModule{

    @Provides
    @GroupsScope
    public GroupsPresenter provideGroupsPresenter(ScheduleRepository groupsRepository) {
        return new GroupsPresenterImpl(groupsRepository);
    }
}