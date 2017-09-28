package heyalex.com.miet_schedule.data;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.DaoMaster;
import heyalex.com.miet_schedule.DaoSession;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.lessons.LessonsRepositoryImpl;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.data.news.NewsRepositoryImpl;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepositoryImpl;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractor;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractorImpl;
import heyalex.com.miet_schedule.data.shortcut.ShortcutPreference;
import heyalex.com.miet_schedule.data.shortcut.ShortcutPreferenceImpl;

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

