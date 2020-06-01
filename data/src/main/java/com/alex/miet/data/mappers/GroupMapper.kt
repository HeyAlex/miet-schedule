package com.alex.miet.data.mappers

import com.alex.miet.data.entities.Group

class GroupMapper : Mapper<Pair<String, String>, Group> {
    override fun map(from: Pair<String, String>): Group =
        Group(name = from.first, semester = from.second)
}