package com.example.cryptohooker.data.remote.apiservice

import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.model.NewsFeedModel
import com.example.cryptohooker.data.remote.ApiResponse
import retrofit2.http.GET

interface CryptoApiInterface {

    @GET(GET_MOVIES)
    suspend fun getMovieList(): ApiResponse<ArrayList<MovieModel>>

    @GET(GET_NEWS_FEED)
    suspend fun getNewsFeed(): ApiResponse<ArrayList<NewsFeedModel>>

    companion object {
        const val GET_MOVIES = "v2/5dea8d843000001d442b09da"
        const val GET_NEWS_FEED = "v2/5dea8bf6300000d23f2b09d0"
    }
}