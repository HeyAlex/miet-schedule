package heyalex.com.miet_schedule;


import javax.inject.Singleton;

import dagger.Component;
import heyalex.com.miet_schedule.data.DataModule;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.schedule_widget.LessonsViewsFactory;
import heyalex.com.miet_schedule.schedule_widget.ScheduleUpdateService;
import heyalex.com.miet_schedule.shortcut.ShortcutPreference;
import heyalex.com.miet_schedule.shortcut.ShortcutPreferenceModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavDrawerModule.class,
        DataModule.class,
        ShortcutPreferenceModule.class
})

public interface ApplicationComponent {

    ShortcutPreference getShortcutPreference();

    NavDrawerPresenter getNavDrawerPresenter();

    NewsRepository newsRepository();

    ScheduleRepository scheduleRepository();

    LessonsRepository lessonsRepository();

    void inject(ScheduleUpdateService service);

    void inject(LessonsViewsFactory service);
}
