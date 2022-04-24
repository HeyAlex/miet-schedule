package com.miet.alex.data.mappers

import com.miet.alex.data.entities.GroupItem
import javax.inject.Inject

class GroupNameMapper @Inject constructor() : Mapper<String, GroupItem> {
    override suspend fun map(from: String): GroupItem = GroupItem(group = from)
}