package heyalex.com.miet_schedule.data.schedule;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.ScheduleModelDao;

/**
 * Created by mac on 13.04.17.
 */

public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleModelDao dao;

    public ScheduleRepositoryImpl(ScheduleModelDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(ScheduleModel entity) {
        dao.save(entity);
    }

    @Override
    public void saveAll(Iterable<ScheduleModel> entities) {
        dao.saveInTx(entities);
    }

    @Override
    public List<ScheduleModel> getAll() {
        return dao.loadAll();
    }

    @Override
    public void update(ScheduleModel entity) {
        dao.update(entity);
    }

    @Override
    public void updateAll(Iterable<ScheduleModel> entities) {
        dao.updateInTx(entities);
    }

    @Override
    public void delete(ScheduleModel entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public ScheduleModel getGroupByName(String groupName) {
        return dao.queryBuilder().where(ScheduleModelDao.Properties.Group.eq(groupName)).build()
                .unique();
    }

    @Override
    public void replaceByGroupName(String groupName, ScheduleModel model) {
        deleteByGroupName(groupName);
        dao.insertOrReplace(model);
    }

    @Override
    public void deleteByGroupName(String groupName) {
        ScheduleModel scheduleByGroup = dao.queryBuilder()
                .where(ScheduleModelDao.Properties.Group.eq(groupName)).build().unique();
        if (scheduleByGroup != null) {
            delete(scheduleByGroup);
        }
    }
}
