package com.example.cryptohooker.di

import com.example.cryptohooker.data.remote.ApiInterface
import com.example.cryptohooker.data.repository.movie.MoviesRepository
import com.example.cryptohooker.data.repository.movie.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePhotosRepository(apiService: ApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(apiService)
    }
}
