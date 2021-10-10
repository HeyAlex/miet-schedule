package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Day") val day: Int,
    @SerializedName("DayNumber") val dayNumber: Int,
    @SerializedName("Time") val time: Time,
    @SerializedName("Class") val slot: Slot,
    @SerializedName("Group") val group: Group,
    @SerializedName("Room") val room: Room
)