package com.moviedb.movieapp.network

import retrofit2.http.GET

interface MovieApi {

    @GET("movies")
    suspend fun getMovies()
}