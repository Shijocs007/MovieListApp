package com.moviedb.movieapp

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private  val TAG = "MovieListActivity"

    private val viewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getMovieList()

        registerObservers()
    }

    private fun registerObservers() {

        viewModel.getMoviesLivedata().observe(this, Observer {

        })

        viewModel.getErrorLivedata().observe(this, Observer {

        })
    }
}
