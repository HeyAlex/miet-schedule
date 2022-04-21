package com.miet.alex.data.repositories

import com.miet.alex.data.daos.GroupDao
import com.miet.alex.data.entities.GroupItem
import javax.inject.Inject

class LocalGroupDataSource @Inject constructor(private val groupDao: GroupDao) {

    suspend fun getGroup(groupName: String): GroupItem? = groupDao.getGroupByName(groupName)

    suspend fun insertOrUpdateGroups(groups: List<GroupItem>) = groupDao.insertOrUpdate(groups)

    suspend fun insert(group: GroupItem) = groupDao.insert(group)

    suspend fun update(group: GroupItem) = groupDao.update(group)

    fun getGroups() = groupDao.loadAll()
}