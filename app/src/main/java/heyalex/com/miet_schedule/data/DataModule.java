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

/**
 * Created by alexf on 05.05.2017.
 */
@Module
public class DataModule {
    private static final String DATABASE_NAME = "miet.db";
    private final NewsRepository newsRepository;
    private final ScheduleRepository scheduleRepository;
    private final LessonsRepository lessonsRepository;

    public DataModule(Context context) {
        DaoSession daoSession = createDaoSession(context);
        this.newsRepository = new NewsRepositoryImpl(daoSession.getNewsModelDao());
        this.scheduleRepository = new ScheduleRepositoryImpl(daoSession.getScheduleModelDao());
        this.lessonsRepository = new LessonsRepositoryImpl(daoSession.getLessonModelDao());
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

}

