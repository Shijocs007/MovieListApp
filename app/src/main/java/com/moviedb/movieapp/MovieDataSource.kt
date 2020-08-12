package com.moviedb.movieapp

import androidx.paging.PageKeyedDataSource
import com.moviedb.movieapp.models.Movie

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        // show progress
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("Not yet implemented")
    }
}