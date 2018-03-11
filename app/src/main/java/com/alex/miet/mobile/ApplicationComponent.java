package com.alex.miet.mobile;


import javax.inject.Singleton;

import dagger.Component;
import com.alex.miet.mobile.data.DataModule;
import com.alex.miet.mobile.data.lessons.LessonsRepository;
import com.alex.miet.mobile.data.news.NewsRepository;
import com.alex.miet.mobile.data.schedule.ScheduleRepository;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;
import com.alex.miet.mobile.data.shortcut.ShortcutPreference;
import com.alex.miet.mobile.navdrawer.NavDrawerModule;
import com.alex.miet.mobile.navdrawer.NavDrawerPresenter;
import com.alex.miet.mobile.schedule_widget.LessonsViewsFactory;
import com.alex.miet.mobile.schedule_widget.ScheduleUpdateService;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavDrawerModule.class,
        DataModule.class
})

public interface ApplicationComponent {

    ShortcutPreference getShortcutPreference();

    ScheduleInteractor getScheduleInteractor();

    NavDrawerPresenter getNavDrawerPresenter();

    NewsRepository newsRepository();

    ScheduleRepository scheduleRepository();

    LessonsRepository lessonsRepository();

    void inject(ScheduleUpdateService service);

    void inject(LessonsViewsFactory service);
}
