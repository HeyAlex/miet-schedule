package com.alex.miet.miet_api

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class MietApiTest {

    @Test
    fun test() {
        runBlocking {
            println(MietApi().getGroups())
        }
    }
}