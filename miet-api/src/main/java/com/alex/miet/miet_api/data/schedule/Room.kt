package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Room (

    @SerializedName("Code") val code : Int,
    @SerializedName("Name") val name : String
)