package heyalex.com.miet_schedule.data.lessons;

import java.util.List;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.LessonModelDao;

/**
 * Created by mac on 09.05.17.
 */

public class LessonsRepositoryImpl implements LessonsRepository {
    private final LessonModelDao dao;

    public LessonsRepositoryImpl(LessonModelDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(LessonModel entity) {
        dao.save(entity);
    }

    @Override
    public void saveAll(Iterable<LessonModel> entities) {
        dao.saveInTx(entities);
    }

    @Override
    public List<LessonModel> getAll() {
        return dao.loadAll();
    }

    @Override
    public void update(LessonModel entity) {
        dao.update(entity);
    }

    @Override
    public void updateAll(Iterable<LessonModel> entities) {
        dao.updateInTx(entities);
    }

    @Override
    public void delete(LessonModel entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public void deleteAllByGroupName(String groupName) {
        Iterable<LessonModel> lessonsByGroup = dao.queryBuilder()
                .where(LessonModelDao.Properties.GroupName.eq(groupName)).build().list();
        if(lessonsByGroup.iterator().hasNext()){
            dao.deleteInTx(lessonsByGroup);
        }
    }

    @Override
    public void replaceAllByGroupName(String groupName, Iterable<LessonModel> lessons) {
        deleteAllByGroupName(groupName);
        saveAll(lessons);
    }

    @Override
    public List<LessonModel> getLessonsByWeekAndDay(String groupName, int week, int day) {
        return dao.queryBuilder().where(LessonModelDao.Properties.GroupName.eq(groupName),
                                        LessonModelDao.Properties.Week.eq(week),
                                        LessonModelDao.Properties.Day.eq(day)).build().list();
    }

    @Override
    public List<LessonModel> getLessonsForWeek(String groupName, int week) {
        return dao.queryBuilder().where(LessonModelDao.Properties.GroupName.eq(groupName),
                LessonModelDao.Properties.Week.eq(week)).build().list();
    }
}
