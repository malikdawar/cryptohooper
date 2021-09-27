package com.example.cryptohooker.data.usecases

import com.example.cryptohooker.data.repository.movies.MoviesRepository
import javax.inject.Inject

/**
 * A use-case to load the products from API.
 * @author Malik Dawar
 */
class FetchMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.fetchMovieList()
}