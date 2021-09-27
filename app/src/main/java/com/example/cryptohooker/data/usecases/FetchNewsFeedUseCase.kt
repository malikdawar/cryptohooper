package com.example.cryptohooker.data.usecases

import com.example.cryptohooker.data.repository.newsfeed.NewsFeedRepository
import javax.inject.Inject

/**
 * A use-case to load the reviews from API.
 * @author Malik Dawar
 */
class FetchNewsFeedUseCase @Inject constructor(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() = repository.fetchNewsFeedList()
}