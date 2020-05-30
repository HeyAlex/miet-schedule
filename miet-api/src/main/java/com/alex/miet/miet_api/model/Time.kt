package com.alex.miet.miet_api.model


import com.google.gson.annotations.SerializedName

data class Time(

    /**
     * number of exercise in all schedule time
     */
    @SerializedName("Code") val code: Int,
    /**
     * String of representation of number of exercise in all schedule time
     */
    @SerializedName("Time") val time: String,
    /**
     * Lesson time start
     */
    @SerializedName("TimeFrom") val timeFrom: String,
    /**
     * Lesson time end
     */
    @SerializedName("TimeTo") val timeTo: String
)