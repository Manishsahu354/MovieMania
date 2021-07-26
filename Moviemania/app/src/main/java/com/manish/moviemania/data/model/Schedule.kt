package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Schedule(
    @SerializedName("days")
    val days: List<String>,
    @SerializedName("time")
    val time: String
): Serializable