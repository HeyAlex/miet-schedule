package com.alex.miet.miet_api.model

import com.google.gson.annotations.SerializedName

data class Semester(

    /**
     * Schedule times of studies
     */
    @SerializedName("Times") val times: List<Times>,
    /**
     * All lessons for current group
     */
    @SerializedName("Data") val data: List<Data>,
    /**
     * Name of semester
     */
    @SerializedName("Semestr") val semestr: String
)