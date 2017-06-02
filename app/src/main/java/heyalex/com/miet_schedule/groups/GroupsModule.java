package heyalex.com.miet_schedule.groups;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.shortcut.ShortcutPreference;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class GroupsModule {

    @Provides
    @GroupsScope
    /*package*/ GroupsPresenter provideGroupsPresenter(ScheduleRepository groupsRepository, ShortcutPreference shortcutPreference) {
        return new GroupsPresenterImpl(groupsRepository, shortcutPreference);
    }
}