package com.miet.alex.data.repositories

import com.miet.alex.data.entities.GroupItem

interface GroupDataSource {
    suspend fun getGroup(groupName: String): GroupItem
    suspend fun insertOrUpdateGroup(group: GroupItem)
    suspend fun delete(groupName: String)
}