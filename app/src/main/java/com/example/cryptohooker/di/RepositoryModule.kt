package com.example.cryptohooker.di

import com.example.cryptohooker.data.remote.apiservice.CryptoApiInterface
import com.example.cryptohooker.data.repository.movies.MoviesRepository
import com.example.cryptohooker.data.repository.movies.MoviesRepositoryImpl
import com.example.cryptohooker.data.repository.newsfeed.NewsFeedRepository
import com.example.cryptohooker.data.repository.newsfeed.NewsFeedRepositoryImpl
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
    fun provideMoviesRepository(cryptoApiService: CryptoApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(cryptoApiService)
    }

    @Singleton
    @Provides
    fun provideNewsFeedRepository(cryptoApiService: CryptoApiInterface): NewsFeedRepository {
        return NewsFeedRepositoryImpl(cryptoApiService)
    }
}
