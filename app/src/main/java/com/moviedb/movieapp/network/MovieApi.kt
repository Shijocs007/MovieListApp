package com.moviedb.movieapp.network

import com.moviedb.movieapp.network.model.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing?api_key=6bf8ddb55a7ad70071bfa67e26407194&&PAGE=2")
    suspend fun getMovies() : Response<MovieResult>
}