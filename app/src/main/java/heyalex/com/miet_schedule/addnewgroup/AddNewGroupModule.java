package heyalex.com.miet_schedule.addnewgroup;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.shortcut.ShortcutPreference;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class AddNewGroupModule {

    @Provides
    @AddNewGroupScope
    /*package*/ AddNewGroupPresenter provideAddNewGroupPresenter(ScheduleRepository groupsRepository,
                                                                 LessonsRepository lessonsRepository,
                                                                 ShortcutPreference shortcutPreference) {
        return new AddNewGroupPresenterImpl(groupsRepository, lessonsRepository, shortcutPreference);
    }
}