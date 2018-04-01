package com.alex.miet.mobile.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Debug;

import com.alex.miet.mobile.MietDatabase;
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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private static final String DATABASE_NAME = "miet.db";
    private final NewsRepository newsRepository;
    private final ScheduleRepository scheduleRepository;
    private final LessonsRepository lessonsRepository;
    private final ScheduleInteractor scheduleInteractor;
    private final ShortcutPreference shortcutPreference;

    public DataModule(Context context) {
        MietDatabase db = createRoomDatabase(context);
        this.newsRepository = new NewsRepositoryImpl(db.newsDao());
        this.scheduleRepository = new ScheduleRepositoryImpl(db.groupDao());
        this.lessonsRepository = new LessonsRepositoryImpl(db.lessonDao());
        this.shortcutPreference = new ShortcutPreferenceImpl(context);
        this.scheduleInteractor = new ScheduleInteractorImpl(scheduleRepository, lessonsRepository,
                shortcutPreference);
    }

    private MietDatabase createRoomDatabase(Context context) {
        RoomDatabase.Builder<MietDatabase> builder = Room.databaseBuilder(context, MietDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration();
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries();
        }
        return builder.build();
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

