package com.example.cryptohooker.data.repository.movies

import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [MoviesRepositoryImpl] for implementation of this class to utilize Crypto API.
 * @author Malik Dawar
 */
interface MoviesRepository {
    suspend fun fetchMovieList(): Flow<DataState<ArrayList<MovieModel>>>
}