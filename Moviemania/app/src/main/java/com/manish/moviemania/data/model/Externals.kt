package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Externals(
    @SerializedName("imdb")
    val imdb: String,
    @SerializedName("thetvdb")
    val thetvdb: Int,
    @SerializedName("tvrage")
    val tvrage: Int
): Serializable