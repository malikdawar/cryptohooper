package com.example.cryptohooker.data.repository.movie

import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * MoviesRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [MoviesRepositoryImpl] for implementation of this class to utilize API.
 * @author Malik Dawar
 */
interface MoviesRepository {

    suspend fun getMovieList(): Flow<DataState<ArrayList<MovieModel>>>
}