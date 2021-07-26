package com.manish.moviemania.ui.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manish.moviemania.data.model.MovieResponseItem
import com.manish.moviemania.repository.Repository
import com.manish.moviemania.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application){

    var moviePage = 1
    var movieResponse: MutableList<MovieResponseItem>? = null

    var responseData: MutableLiveData<NetworkResult<MutableList<MovieResponseItem>>> =
        MutableLiveData()

    init {
        getMovieData()
    }

    fun getMovieData(){

        viewModelScope.launch {

            safeMovieListCall()

        }

    }

    private suspend fun safeMovieListCall() {

        responseData.value = NetworkResult.Loading()

        try {

            if (hasInternetConnection()) {
                val response = repository.showMovies(moviePage)

                responseData.value = handleMovieResponse(response)

            } else {
                responseData.value = NetworkResult.Error("No Internet Connection")
            }
        }catch (t: Throwable){

            when(t){
                is IOException -> responseData.postValue(NetworkResult.Error("Network Failure"))
                else -> responseData.postValue(NetworkResult.Error("Conversion Error"))
            }

        }
    }

    private fun handleMovieResponse(response: Response<MutableList<MovieResponseItem>>
                      ):NetworkResult<MutableList<MovieResponseItem>>{

        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                moviePage++
                if (movieResponse == null){
                    movieResponse = resultResponse
                }else{
                    val oldMovieResponse = movieResponse
                    val newMovieResponse = resultResponse
                    oldMovieResponse?.addAll(newMovieResponse)
                }
                return NetworkResult.Success(movieResponse?: resultResponse)
            }
        }
        return NetworkResult.Error(response.message())
    }



    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}