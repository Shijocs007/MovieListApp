package com.moviedb.movieapp.network

class NetworkState( val msg: String) {

    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState("Success")

            LOADING = NetworkState("Running")

            ERROR = NetworkState( "Something went wrong")

            ENDOFLIST = NetworkState( "You have reached the end")
        }
    }
}