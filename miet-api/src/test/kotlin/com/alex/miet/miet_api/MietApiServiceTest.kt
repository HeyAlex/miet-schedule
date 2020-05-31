package com.alex.miet.miet_api

import com.alex.miet.miet_api.network.MietApiService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test

class MietApiServiceTest {

    @Test
    fun test() {
        val client = OkHttpClient()
        val api = MietApiService.create(client)
        runBlocking {
            val groups = api.groups()
            println(groups)
            println(api.schedule(groups.first()))
        }
    }
}