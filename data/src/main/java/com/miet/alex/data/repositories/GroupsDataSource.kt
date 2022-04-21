package com.miet.alex.data.repositories

import com.alex.miet.miet_api.MietApiService
import com.miet.alex.data.entities.GroupItem
import com.miet.alex.data.mappers.GroupNameMapper
import javax.inject.Inject

class GroupsDataSource @Inject constructor(
    private val mietApiService: MietApiService,
    private val groupNameMapper: GroupNameMapper
) {
    suspend fun getGroups(): List<GroupItem> {
        return mietApiService.groups().map {
            groupNameMapper.map(it)
        }
    }
}