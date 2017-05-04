package heyalex.com.miet_schedule.data;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.DaoMaster;
import heyalex.com.miet_schedule.DaoSession;
import heyalex.com.miet_schedule.data.news.NewsRepository;
import heyalex.com.miet_schedule.data.news.NewsRepositoryImpl;

/**
 * Created by alexf on 05.05.2017.
 */
@Module
public class DataModule {
    private static final String DATABASE_NAME = "miet.db";
    private final NewsRepository newsRepository;

    public DataModule(Context context) {
        DaoSession daoSession = createDaoSession(context);
        this.newsRepository = new NewsRepositoryImpl(daoSession.getNewsModelDao());
    }

    private DaoSession createDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    @Singleton
    public NewsRepository provideNewsRepository() {
        return newsRepository;
    }

}

