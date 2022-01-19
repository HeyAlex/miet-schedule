package com.miet.alex.data.repositories

class GroupsRepository(
    private val groupsDataSource: GroupsDataSource,
    private val groupsStore: GroupsStore
) {
    suspend fun observeGroups() = groupsStore.getGroups()
    suspend fun loadGroups() {
        groupsDataSource.getGroups().also {
            groupsStore.insertOrUpdateGroups(it)
        }
    }
}