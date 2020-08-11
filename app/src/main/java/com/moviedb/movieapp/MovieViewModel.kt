package com.moviedb.movieapp

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MovieViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun setTestData() {
        Log.d("ta", "setTestData: ")
    }

}