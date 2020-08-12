package com.moviedb.movieapp

import android.telecom.Call
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.network.SafeApiRequest
import com.moviedb.movieapp.network.model.MovieResult
import com.moviedb.movieapp.room.MovieDatabase
import com.moviedb.movieapp.test.TestResp

class MovieRepository
constructor(
    private val api : MovieApi,
    private val db : MovieDatabase
) : SafeApiRequest() {

    suspend fun getMoviesFromCloud() : MovieResult{
        return apiRequest { api.getMovies(1) }
    }

    suspend fun getMoviesFromDb(searchKey : String) = db.movieDao().getMovies(searchKey)

    suspend fun saveMoviesToDB(movies: List<Movie>) = db.movieDao().upsertAll(movies)
}