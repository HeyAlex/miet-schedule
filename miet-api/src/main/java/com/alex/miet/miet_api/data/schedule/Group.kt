package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("Code") val code: String,
    /**
     * Group name
     */
    @SerializedName("Name") val name: String
)