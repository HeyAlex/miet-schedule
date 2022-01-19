package com.miet.alex.data.mappers

import com.miet.alex.data.entities.GroupItem

class GroupNameMapper : Mapper<String, GroupItem> {
    override suspend fun map(from: String): GroupItem = GroupItem(group = from)
}