package com.miet.alex.data.repositories

import javax.inject.Inject

class GroupsRepository @Inject constructor(
    private val groupsDataSource: GroupsDataSource,
    private val groupsStore: LocalGroupDataSource
) {
    fun observeGroups() = groupsStore.getGroups()

    suspend fun loadGroups() {
        val groups = groupsDataSource.getGroups()
        groupsStore.insertOrUpdateGroups(groups)

    }
}