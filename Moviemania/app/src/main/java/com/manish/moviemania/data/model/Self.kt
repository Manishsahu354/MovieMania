package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Self(
    @SerializedName("href")
    val href: String
): Serializable