package com.alex.miet.miet_api.model

import com.google.gson.annotations.SerializedName

data class Room(

    @SerializedName("Code") val code: Int,
    /**
     * Name of classroom
     */
    @SerializedName("Name") val name: String
)