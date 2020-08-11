package com.moviedb.movieapp.di

import com.moviedb.movieapp.MovieRepository
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi, movieDatabase: MovieDatabase) : MovieRepository {
        return MovieRepository(api, movieDatabase);
    }
}