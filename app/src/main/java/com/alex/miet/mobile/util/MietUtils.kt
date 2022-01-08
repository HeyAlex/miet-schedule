package com.alex.miet.mobile.util

import org.joda.time.DateTime

object MietUtils {
    private val MILLIS_PER_WEEK = Time.DAYS.toMillis(7)
    private const val WEEKS_PER_SEMESTER = 18
    private val startOfAutumnSemester: DateTime
    private val endOfAutumnSemester: DateTime
    private val startOfSpringSemester: DateTime
    private val endOfSpringSemester: DateTime

    init {
        startOfAutumnSemester = DateTime.now()
        startOfAutumnSemester.set(Calendar.MONTH, Calendar.SEPTEMBER)
        startOfAutumnSemester.set(Calendar.DAY_OF_MONTH, 1)
        startOfAutumnSemester.set(Calendar.HOUR_OF_DAY, 0)
        endOfAutumnSemester = startOfAutumnSemester.clone() as Calendar
        endOfAutumnSemester.add(Calendar.WEEK_OF_YEAR, WEEKS_PER_SEMESTER)
        endOfAutumnSemester[Calendar.HOUR_OF_DAY] = 24
        val newYear = DateTime.now().year().
        if (endOfAutumnSemester > newYear) {
            endOfAutumnSemester.timeInMillis =
                newYear.timeInMillis
        }
        startOfSpringSemester = Calendar.getInstance()
        startOfSpringSemester.set(Calendar.ERA, GregorianCalendar.AD)
        startOfSpringSemester[Calendar.MONTH] = Calendar.FEBRUARY
        startOfSpringSemester[Calendar.DAY_OF_MONTH] = 13
        startOfSpringSemester[Calendar.HOUR_OF_DAY] = 0
        endOfSpringSemester = startOfSpringSemester.clone() as Calendar
        endOfSpringSemester.add(Calendar.WEEK_OF_YEAR, WEEKS_PER_SEMESTER)
        endOfSpringSemester[Calendar.HOUR_OF_DAY] = 24
        floorToFirstDayOfWeek(startOfAutumnSemester)
        floorToFirstDayOfWeek(endOfAutumnSemester)
        floorToFirstDayOfWeek(startOfSpringSemester)
        floorToFirstDayOfWeek(endOfSpringSemester)
    }

    private fun floorToFirstDayOfWeek(calendar: Calendar) {
        calendar.add(
            Calendar.DAY_OF_YEAR,
            -calendar[Calendar.DAY_OF_WEEK] + calendar.firstDayOfWeek
        )
    }

    private fun belongsToAutumnSemester(calendar: Calendar): Boolean {
        return (calendar.timeInMillis in startOfAutumnSemester.timeInMillis..endOfAutumnSemester.timeInMillis)
    }

    private fun belongsToSpringSemester(calendar: Calendar): Boolean {
        return (calendar.timeInMillis in startOfSpringSemester.timeInMillis..endOfSpringSemester.timeInMillis)
    }

    fun getWeekOfSemesterByDateTime(date: DateTime): Int {

        if (belongsToAutumnSemester(date)) {
            val millisDiff = date.millis - startOfAutumnSemester.timeInMillis
            return (millisDiff / MILLIS_PER_WEEK + 1).toInt()
        } else if (belongsToSpringSemester(calendar)) {
            val millisDiff = date.millis - startOfSpringSemester.timeInMillis
            return (millisDiff / MILLIS_PER_WEEK + 1).toInt()
        }
        return -1
    }
}