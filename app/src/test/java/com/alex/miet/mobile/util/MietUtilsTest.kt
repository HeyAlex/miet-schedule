package com.alex.miet.mobile.util

import junit.framework.TestCase
import java.util.*

class MietUtilsTest : TestCase() {

    fun test() {
        assertEquals(1, MietUtils.getWeekOfSemesterByDateTime(Date(2021, 9, 1)))
    }
}