package com.alex.miet.miet_api.data.schedule

import com.google.gson.annotations.SerializedName

data class Semestr (
    @SerializedName("Times") val times : List<Times>,
    @SerializedName("Data") val data : List<Data>,
    @SerializedName("Semestr") val semestr : String
)