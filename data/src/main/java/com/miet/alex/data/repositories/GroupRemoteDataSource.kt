package com.miet.alex.data.repositories

import com.miet.alex.data.entities.GroupItem

interface GroupRemoteDataSource {
    suspend fun getGroupNames(): List<GroupItem>
}