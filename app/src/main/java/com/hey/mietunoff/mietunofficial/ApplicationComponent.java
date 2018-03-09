package com.hey.mietunoff.mietunofficial;


import javax.inject.Singleton;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.data.DataModule;
import com.hey.mietunoff.mietunofficial.data.lessons.LessonsRepository;
import com.hey.mietunoff.mietunofficial.data.news.NewsRepository;
import com.hey.mietunoff.mietunofficial.data.schedule.ScheduleRepository;
import com.hey.mietunoff.mietunofficial.data.shared_interactor.ScheduleInteractor;
import com.hey.mietunoff.mietunofficial.data.shortcut.ShortcutPreference;
import com.hey.mietunoff.mietunofficial.navdrawer.NavDrawerModule;
import com.hey.mietunoff.mietunofficial.navdrawer.NavDrawerPresenter;
import com.hey.mietunoff.mietunofficial.schedule_widget.LessonsViewsFactory;
import com.hey.mietunoff.mietunofficial.schedule_widget.ScheduleUpdateService;

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
