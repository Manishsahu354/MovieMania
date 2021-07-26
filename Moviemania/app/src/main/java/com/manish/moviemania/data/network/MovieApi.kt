package com.manish.moviemania.data.network

import com.manish.moviemania.data.model.MovieResponseItem
import com.manish.moviemania.util.Constants.END_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {


    @GET(END_URL)
    suspend fun getMovies(

        @Query("page")page:Int
    ): Response<MutableList<MovieResponseItem>>


}