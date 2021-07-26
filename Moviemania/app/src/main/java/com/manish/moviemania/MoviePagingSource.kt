package com.manish.moviemania


import com.manish.moviemania.data.network.MovieApi


private const val UNSPLASH_STARTING_PAGE_INDEX = 1
class MoviePagingSource(
    private val movieApi: MovieApi
){

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseItem> {
//        val page = params.key ?:UNSPLASH_STARTING_PAGE_INDEX
//        return try {
//            val response = movieApi.getMovies(page)
//            LoadResult.Page(
//                data = response,
//                prevKey = if (page==UNSPLASH_STARTING_PAGE_INDEX) null else page-1,
//                nextKey = if (response.isEmpty()) null else page+1
//            )
//        }catch (exception: IOException){
//            LoadResult.Error(exception)
//
//        }catch (exception: HttpException){
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, MovieResponseItem>): Int? {
//        return null
//    }


}