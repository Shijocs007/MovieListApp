package com.moviedb.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.MovieApi

class MovieDataSourceFactory(private val api: MovieApi) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(api)
        moviesLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}