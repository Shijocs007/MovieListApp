package com.moviedb.movieapp

import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.room.MovieDatabase

class MovieRepository
constructor(
    private val api : MovieApi,
    private val db : MovieDatabase
) {

    init {
        print("")
    }

}