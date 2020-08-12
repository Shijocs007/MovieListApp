package com.moviedb.movieapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moviedb.movieapp.network.MovieApi
import com.moviedb.movieapp.network.NetworkConnectionIntercepter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context) : NetworkConnectionIntercepter {
        return NetworkConnectionIntercepter(context)
    }

    @Singleton
    @Provides
    fun provideOkhttp(networkConnectionIntercepter: NetworkConnectionIntercepter) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionIntercepter)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit.Builder): MovieApi {
        return retrofit
            .build()
            .create(MovieApi::class.java)
    }

}