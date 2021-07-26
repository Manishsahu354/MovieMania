package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue
import java.io.Serializable


data class Network(
    @SerializedName("country")
    val country: @RawValue Country,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
):Serializable