package com.alex.miet.mobile.data;

import android.content.Context;

import com.alex.miet.mobile.data.lessons.LessonsRepository;
import com.alex.miet.mobile.data.lessons.LessonsRepositoryImpl;
import com.alex.miet.mobile.data.news.NewsRepository;
import com.alex.miet.mobile.data.news.NewsRepositoryImpl;
import com.alex.miet.mobile.data.schedule.ScheduleRepository;
import com.alex.miet.mobile.data.schedule.ScheduleRepositoryImpl;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractorImpl;
import com.alex.miet.mobile.data.shortcut.ShortcutPreference;
import com.alex.miet.mobile.data.shortcut.ShortcutPreferenceImpl;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.alex.miet.mobile.DaoMaster;
import com.alex.miet.mobile.DaoSession;

@Module
public class DataModule {

    private static final String DATABASE_NAME = "miet.db";
    private final NewsRepository newsRepository;
    private final ScheduleRepository scheduleRepository;
    private final LessonsRepository lessonsRepository;
    private final ScheduleInteractor scheduleInteractor;
    private final ShortcutPreference shortcutPreference;

    public DataModule(Context context) {
        DaoSession daoSession = createDaoSession(context);
        this.newsRepository = new NewsRepositoryImpl(daoSession.getNewsModelDao());
        this.scheduleRepository = new ScheduleRepositoryImpl(daoSession.getScheduleModelDao());
        this.lessonsRepository = new LessonsRepositoryImpl(daoSession.getLessonModelDao());
        this.shortcutPreference = new ShortcutPreferenceImpl(context);
        this.scheduleInteractor = new ScheduleInteractorImpl(scheduleRepository, lessonsRepository,
                shortcutPreference);
    }

    private DaoSession createDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    @Singleton
    /*package*/ NewsRepository provideNewsRepository() {
        return newsRepository;
    }

    @Provides
    @Singleton
    /*package*/ ScheduleRepository provideScheduleRepository() {
        return scheduleRepository;
    }

    @Provides
    @Singleton
    /*package*/ LessonsRepository provideLessonssRepository() {
        return lessonsRepository;
    }

    @Provides
    @Singleton
    /*package*/ ScheduleInteractor provideScheduleInteractor() {
        return scheduleInteractor;
    }

    @Provides
    @Singleton
    /*package*/ ShortcutPreference provideShortcutPreferences() {
        return shortcutPreference;
    }
}

