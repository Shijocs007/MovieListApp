package com.moviedb.movieapp

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.Coroutines
import com.moviedb.movieapp.utils.ApiException
import com.moviedb.movieapp.utils.NoInternetException

class MovieViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var moviesList = MutableLiveData<List<Movie>>()
    private var errorLivedata = MutableLiveData<String>()

    fun getMovieList() {
        Coroutines.main {
            try {
                val result = repository.getMoviesFromCloud()
                if(result.movies.isNullOrEmpty()) {
                    errorLivedata.value = "No items to "
                } else{
                    moviesList.value = result.movies
                    repository.saveMoviesToDB(result.movies)
                }
            } catch ( e : ApiException) {
                errorLivedata.value = e.message
            } catch (e : NoInternetException) {
                val result = repository.getMoviesFromDb()
                result.let {
                    moviesList.value = it
                }
                errorLivedata.value = e.message
            }
        }
    }

    fun getMoviesLivedata() : LiveData<List<Movie>> {
        return moviesList;
    }

    fun getErrorLivedata() : LiveData<String> {
        return errorLivedata
    }
}