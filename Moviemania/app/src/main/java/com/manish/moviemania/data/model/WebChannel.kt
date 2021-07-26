package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue
import java.io.Serializable

data class WebChannel(
    @SerializedName("country")
    val country: @RawValue CountryX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Serializable