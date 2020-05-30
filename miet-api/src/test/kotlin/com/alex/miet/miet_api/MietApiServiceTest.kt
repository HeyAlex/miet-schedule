package com.alex.miet.miet_api

import com.alex.miet.miet_api.network.MietApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MietApiServiceTest {

    @Test
    fun test() {
        runBlocking {
            val groups = MietApiService.create().groups()
            println(groups)
            println(MietApiService.create(). schedule(groups.first()))
        }
    }
}