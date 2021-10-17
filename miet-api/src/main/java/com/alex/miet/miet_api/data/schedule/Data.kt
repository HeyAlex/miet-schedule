package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName


/**
 * This class is response holder with lesson information, includes in [Schedule]
 * Contains information about room, time, week and day
 */
data class Data(
    /**
     * Information about lesson's classroom
     */
    @SerializedName("Room") val room: Room,
    /**
     * Information about lessons' time
     */
    @SerializedName("Time") val time: Time,
    /**
     * Information about lesson's discipline
     */
    @SerializedName("Class") val classRoom: Class,
    /**
     * number of week's lesson from 0..3 (means 1/2 numerator/denumerator)
     */
    @SerializedName("DayNumber") val dayNumber: Int,
    /**
     * number of a lesson's day in a week 0..6
     */
    @SerializedName("Day") val day: Int,
    /**
     * Information about lesson's group
     */
    @SerializedName("Group") val group: Group
)