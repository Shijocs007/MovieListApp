package com.moviedb.movieapp

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.network.SafeApiRequest
import com.moviedb.movieapp.network.model.MovieResult
import com.moviedb.movieapp.room.MovieDatabase

class MovieRepository
constructor(
    private val api : MovieApi,
    private val db : MovieDatabase
) : SafeApiRequest() {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    suspend fun getMoviesFromCloud() : MovieResult{
        return apiRequest { api.getMovies(1) }
    }

    fun fetchMovieList() : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(api)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }

    suspend fun getMoviesFromDb() = db.movieDao().getMovies()

    suspend fun saveMoviesToDB(movies: List<Movie>) = db.movieDao().upsertAll(movies)
}