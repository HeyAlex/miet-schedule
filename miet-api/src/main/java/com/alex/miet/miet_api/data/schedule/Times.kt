package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Times (
    @SerializedName("Time") val time : String,
    @SerializedName("Code") val code : Int,
    @SerializedName("TimeFrom") val timeFrom : String,
    @SerializedName("TimeTo") val timeTo : String
)