package com.example.cryptohooker.di

import com.example.cryptohooker.data.remote.apiservice.CryptoApiInterface
import com.example.cryptohooker.data.repository.movies.MoviesRepository
import com.example.cryptohooker.data.repository.movies.MoviesRepositoryImpl
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
    fun provideProductRepository(cryptoApiService: CryptoApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(cryptoApiService)
    }
}
