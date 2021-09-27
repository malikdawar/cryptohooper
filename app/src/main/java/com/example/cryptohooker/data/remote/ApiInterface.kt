package com.example.cryptohooker.data.remote

import com.example.cryptohooker.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(GET_MOVIES)
    suspend fun getMovieList(): ApiResponse<ArrayList<MovieModel>>

    @GET(GET_NEWS_FEED)
    suspend fun getNewsFeed(): ApiResponse<ArrayList<MovieModel>>

    companion object {
        const val GET_MOVIES = "v2/5dea8d843000001d442b09da"
        const val GET_NEWS_FEED = "v2/5dea8bf6300000d23f2b09d0"
    }
}