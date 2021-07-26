package com.manish.moviemania.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue
import java.io.Serializable

data class MovieResponseItem(
    @SerializedName("averageRuntime")
    val averageRuntime: Int,
    @SerializedName("dvdCountry")
    val dvdCountry: @RawValue Any,
    @SerializedName("externals")
    val externals: @RawValue Externals,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: @RawValue Image,
    @SerializedName("language")
    val language: String,
    @SerializedName("_links")
    val links: @RawValue Links,
    @SerializedName("name")
    val name: String,
    @SerializedName("network")
    val network: @RawValue Network,
    @SerializedName("officialSite")
    val officialSite: String,
    @SerializedName("premiered")
    val premiered: String,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("schedule")
    val schedule: Schedule,
    @SerializedName("status")
    val status: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("webChannel")
    val webChannel: WebChannel,
    @SerializedName("weight")
    val weight: Int
):Serializable