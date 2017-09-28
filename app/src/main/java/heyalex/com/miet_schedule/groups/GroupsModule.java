package heyalex.com.miet_schedule.groups;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractor;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class GroupsModule {

    @Provides
    @GroupsScope
    /*package*/ GroupsPresenter provideGroupsPresenter(ScheduleInteractor interactor) {
        return new GroupsPresenterImpl(interactor);
    }
}