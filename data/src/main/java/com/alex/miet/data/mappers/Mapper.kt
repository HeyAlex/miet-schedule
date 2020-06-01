package com.alex.miet.data.mappers

interface Mapper<F, T> {
    fun map(from: F): T
}