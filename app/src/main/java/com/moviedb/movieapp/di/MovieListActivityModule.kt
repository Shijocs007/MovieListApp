package com.moviedb.movieapp.di

import android.content.Context
import com.moviedb.movieapp.adapter.MoviePagedListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object MovieListActivityModule {

    @Provides
    fun providePagedListAdapter(@ActivityContext context: Context)  : MoviePagedListAdapter {
        return MoviePagedListAdapter(context)
    }
}