package com.moviedb.movieapp.di

import android.content.Context
import androidx.room.Room
import com.moviedb.movieapp.room.MovieDao
import com.moviedb.movieapp.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDb(@ApplicationContext context: Context) : MovieDatabase {
        return  Room
            .databaseBuilder(
                context,
                MovieDatabase::class.java,
                MovieDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}