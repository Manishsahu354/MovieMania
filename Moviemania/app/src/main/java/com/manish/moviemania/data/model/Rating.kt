package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("average")
    val average: Double
): Serializable