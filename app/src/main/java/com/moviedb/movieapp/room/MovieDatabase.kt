package com.moviedb.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object {
        val DATABASE_NAME: String = "movie_db"
    }
}