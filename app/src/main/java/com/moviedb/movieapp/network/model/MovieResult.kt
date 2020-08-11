package com.moviedb.movieapp.network.model

import com.google.gson.annotations.SerializedName
import com.moviedb.movieapp.models.Movie


data class MovieResult(
    @SerializedName("results") val movies : List<Movie>,
    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int
)