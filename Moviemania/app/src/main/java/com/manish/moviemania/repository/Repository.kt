package com.manish.moviemania.repository

import com.manish.moviemania.data.network.MovieApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieApi: MovieApi,
) {

    suspend fun showMovies(pageNumber:Int) = movieApi.getMovies(pageNumber )

}