package com.alex.miet.mobile.data.schedule

import com.alex.miet.mobile.daos.GroupDao
import com.alex.miet.mobile.entities.GroupItem
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Specific [ScheduleRepository] implementation
 */
class ScheduleRepositoryImpl(private val dao: GroupDao) : ScheduleRepository {

    override fun save(entity: GroupItem) {
        Single.fromCallable {
            dao.insert(entity)
        }
    }

    override fun saveAll(entities: List<GroupItem>) {
        Single.fromCallable {
            dao.insert(entities)
        }
    }

    override fun getAll(): Maybe<List<GroupItem>> {
        return dao.loadAll()
    }

    override fun update(entity: GroupItem) {
        Single.fromCallable {
            dao.update(entity)
        }
    }

    override fun updateAll(entities: List<GroupItem>) {
        Single.fromCallable {
            dao.update(entities)
        }
    }

    override fun delete(entity: GroupItem) {
        Single.fromCallable {
            dao.delete(entity)
        }
    }

    override fun deleteAll() {
        Single.fromCallable {
            dao.deleteAll()
        }
    }

    override fun getGroupByName(groupName: String): Maybe<GroupItem> {
        return dao.getGroupByName(groupName)
    }

    override fun replaceByGroupName(groupName: String, model: GroupItem) {
        Single.fromCallable {
            deleteByGroupName(groupName)
            dao.insert(model)
        }
    }

    override fun deleteByGroupName(groupName: String) {
        Single.fromCallable {
            val scheduleByGroup = dao.getGroupByName(groupName).blockingGet()
            delete(scheduleByGroup)
        }
    }

    override fun isGroupCached(groupName: String): Boolean {
        return dao.getGroupByName(groupName).blockingGet()?.group == groupName
    }
}
