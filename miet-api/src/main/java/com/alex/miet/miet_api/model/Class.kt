package com.alex.miet.miet_api.model

import com.google.gson.annotations.SerializedName

/**
 * This class is response holder with discipline information, includes in [Data]
 * Contains information about discipline name and teacher that teach that discipline
 */
data class Class(
    @SerializedName("Code") val code: Int,
    /**
     * Discipline name
     */
    @SerializedName("Name") val name: String,
    /**
     * Full teacher name
     */
    @SerializedName("TeacherFull") val teacherFull: String,
    /**
     * Short teacher name
     */
    @SerializedName("Teacher") val teacher: String
)