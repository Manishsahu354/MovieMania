package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CountryX(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: String
): Serializable