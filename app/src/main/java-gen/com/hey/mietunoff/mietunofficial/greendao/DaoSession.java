package com.hey.mietunoff.mietunofficial.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hey.mietunoff.mietunofficial.greendao.NewsModel;
import com.hey.mietunoff.mietunofficial.greendao.ScheduleModel;
import com.hey.mietunoff.mietunofficial.greendao.LessonModel;

import com.hey.mietunoff.mietunofficial.greendao.NewsModelDao;
import com.hey.mietunoff.mietunofficial.greendao.ScheduleModelDao;
import com.hey.mietunoff.mietunofficial.greendao.LessonModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsModelDaoConfig;
    private final DaoConfig scheduleModelDaoConfig;
    private final DaoConfig lessonModelDaoConfig;

    private final NewsModelDao newsModelDao;
    private final ScheduleModelDao scheduleModelDao;
    private final LessonModelDao lessonModelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsModelDaoConfig = daoConfigMap.get(NewsModelDao.class).clone();
        newsModelDaoConfig.initIdentityScope(type);

        scheduleModelDaoConfig = daoConfigMap.get(ScheduleModelDao.class).clone();
        scheduleModelDaoConfig.initIdentityScope(type);

        lessonModelDaoConfig = daoConfigMap.get(LessonModelDao.class).clone();
        lessonModelDaoConfig.initIdentityScope(type);

        newsModelDao = new NewsModelDao(newsModelDaoConfig, this);
        scheduleModelDao = new ScheduleModelDao(scheduleModelDaoConfig, this);
        lessonModelDao = new LessonModelDao(lessonModelDaoConfig, this);

        registerDao(NewsModel.class, newsModelDao);
        registerDao(ScheduleModel.class, scheduleModelDao);
        registerDao(LessonModel.class, lessonModelDao);
    }
    
    public void clear() {
        newsModelDaoConfig.clearIdentityScope();
        scheduleModelDaoConfig.clearIdentityScope();
        lessonModelDaoConfig.clearIdentityScope();
    }

    public NewsModelDao getNewsModelDao() {
        return newsModelDao;
    }

    public ScheduleModelDao getScheduleModelDao() {
        return scheduleModelDao;
    }

    public LessonModelDao getLessonModelDao() {
        return lessonModelDao;
    }

}
