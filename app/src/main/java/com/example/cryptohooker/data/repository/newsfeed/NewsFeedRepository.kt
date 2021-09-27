package com.example.cryptohooker.data.repository.newsfeed

import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.NewsFeedModel
import kotlinx.coroutines.flow.Flow

/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [NewsFeedRepositoryImpl] for implementation of this class to utilize Crypto API.
 * @author Malik Dawar
 */
interface NewsFeedRepository {
    suspend fun fetchNewsFeedList(): Flow<DataState<ArrayList<NewsFeedModel>>>
}