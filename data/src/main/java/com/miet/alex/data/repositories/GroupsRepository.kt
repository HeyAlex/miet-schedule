package com.miet.alex.data.repositories

import com.miet.alex.data.entities.GroupItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GroupsRepository(
    private val groupsDataSource: GroupsDataSource,
    private val groupsStore: GroupsStore
) {
//    suspend fun observeGroups() = groupsStore.getGroups()

    suspend fun loadGroups() : Flow<List<GroupItem>> {
        return flow {
            emit(groupsStore.getGroups())
            val groups = groupsDataSource.getGroups()
            emit(groups)
            groupsStore.insertOrUpdateGroups(groups)
        }

    }
}