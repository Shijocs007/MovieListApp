package com.moviedb.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.Coroutines
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.utils.ApiException
import com.moviedb.movieapp.utils.NoInternetException

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

class MovieDataSource(private val api : MovieApi) : PageKeyedDataSource<Int, Movie>() {

    private var page = 0
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)

        Coroutines.main {
            try {
                val result = api.getMovies(page)
                if(result.isSuccessful) {
                    result.body()?.movies?.let { callback.onResult(it, null, page+1) }
                    networkState.postValue(NetworkState.LOADED)
                } else{
                    networkState.postValue(NetworkState.ERROR)
                }
            } catch ( e : ApiException) {
                networkState.postValue(NetworkState.ERROR)
            } catch (e : NoInternetException) {
               // val result = repository.getMoviesFromDb()
                networkState.postValue(NetworkState.ERROR)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        networkState.postValue(NetworkState.LOADING)

        Coroutines.main {
            try {
                val result = api.getMovies(page)
                if(result.isSuccessful) {
                    if(result.body()?.total_pages!! >= params.key) {
                        result.body()?.movies?.let { callback.onResult(it, params.key+1) }
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        networkState.postValue(NetworkState.ENDOFLIST)
                    }
                } else{
                    networkState.postValue(NetworkState.ERROR)
                }
            } catch ( e : ApiException) {
                networkState.postValue(NetworkState.ERROR)
            } catch (e : NoInternetException) {
                // val result = repository.getMoviesFromDb()
                networkState.postValue(NetworkState.ERROR)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("Not yet implemented")
    }
}