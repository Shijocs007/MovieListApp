package com.moviedb.movieapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.Coroutines
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.repository.MovieRepository
import com.moviedb.movieapp.utils.ApiException
import com.moviedb.movieapp.utils.NoInternetException

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

class MovieDataSource(private val repository: MovieRepository) : PageKeyedDataSource<Int, Movie>() {

    private var page = 1
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)

        Coroutines.main {
            try {
                val result = repository.getMoviesFromCloud()
                if(result?.movies.isNullOrEmpty()) {
                    networkState.postValue(NetworkState.ERROR)
                } else{
                    result.movies?.let {
                        callback.onResult(it, null, page+1)
                        repository.saveMoviesToDB(it)
                    }
                    networkState.postValue(NetworkState.LOADED)
                }
            } catch ( e : ApiException) {
                networkState.postValue(NetworkState.ERROR)
            } catch (e : NoInternetException) {
                val result = repository.getMoviesFromDb()
                result?.let {
                    callback.onResult(it, null, page+1)
                    networkState.postValue(NetworkState.LOADED)
                    return@main
                }
                networkState.postValue(NetworkState.ERROR)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        networkState.postValue(NetworkState.LOADING)

        Coroutines.main {
            try {
                val result = repository.getMoviesFromCloud()
                if(result?.movies.isNullOrEmpty()) {
                    networkState.postValue(NetworkState.ERROR)
                } else{
                    result.movies?.let {
                        callback.onResult(it, params.key+1)
                        repository.saveMoviesToDB(it)
                    }
                    networkState.postValue(NetworkState.LOADED)
                }
            } catch ( e : ApiException) {
                networkState.postValue(NetworkState.ERROR)
            } catch (e : NoInternetException) {
                val result = repository.getMoviesFromDb()
                result?.let {
                    callback.onResult(it, params.key+1)
                    networkState.postValue(NetworkState.LOADED)
                    return@main
                }
                networkState.postValue(NetworkState.ERROR)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("Not yet implemented")
    }
}