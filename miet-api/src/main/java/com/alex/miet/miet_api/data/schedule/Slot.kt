package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Slot (
    @SerializedName("Code") val code : Int,
    @SerializedName("Name") val name : String,
    @SerializedName("TeacherFull") val teacherFull : String,
    @SerializedName("Teacher") val teacher : String,
    @SerializedName("Form") val form : String
)