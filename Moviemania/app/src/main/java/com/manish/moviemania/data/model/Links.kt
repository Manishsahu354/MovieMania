package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue
import java.io.Serializable

data class Links(
    @SerializedName("previousepisode")
    val previousepisode: @RawValue Previousepisode,
    @SerializedName("self")
    val self: Self
): Serializable