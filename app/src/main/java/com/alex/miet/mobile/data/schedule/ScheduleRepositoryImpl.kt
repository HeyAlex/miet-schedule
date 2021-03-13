package com.alex.miet.mobile.data.schedule

import com.alex.miet.mobile.daos.GroupDao
import com.alex.miet.mobile.entities.GroupItem
import io.reactivex.Maybe

/**
 * Specific [ScheduleRepository] implementation
 */
class ScheduleRepositoryImpl(private val dao: GroupDao) : ScheduleRepository {

    override fun save(entity: GroupItem) {
        dao.insert(entity)
    }

    override fun saveAll(entities: List<GroupItem>) {
        dao.insert(entities)
    }

    override fun getAll(): Maybe<List<GroupItem>> {
        return dao.loadAll()
    }

    override fun update(entity: GroupItem) {
        dao.update(entity)
    }

    override fun updateAll(entities: List<GroupItem>) {
        dao.update(entities)
    }

    override fun delete(entity: GroupItem) {
        dao.delete(entity)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun getGroupByName(groupName: String): Maybe<GroupItem> {
        return dao.getGroupByName(groupName)
    }

    override fun replaceByGroupName(groupName: String, model: GroupItem) {
        deleteByGroupName(groupName)
        dao.insert(model)
    }

    override fun deleteByGroupName(groupName: String) {
        val scheduleByGroup = dao.getGroupByName(groupName).blockingGet()
        if (scheduleByGroup != null) {
            delete(scheduleByGroup)
        }
    }

    override fun isGroupCached(groupName: String): Boolean {
        return dao.getGroupByName(groupName).blockingGet()?.group == groupName
    }
}
