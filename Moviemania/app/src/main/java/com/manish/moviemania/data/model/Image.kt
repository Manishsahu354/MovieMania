package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName("medium")
    val medium: String,
    @SerializedName("original")
    val original: String
): Serializable