package com.alex.miet.data.repositories

import com.alex.miet.data.daos.GroupsDao
import com.alex.miet.data.entities.Group
import javax.inject.Inject

class GroupRepository @Inject constructor(private val groupsDao: GroupsDao) {
    suspend fun saveGroup(name: String, semester: String): Long {
        val group = Group(name = name, semester = semester)
        return groupsDao.insert(group)
    }
}