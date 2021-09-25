package com.example.cryptohooker.data.remote

import com.example.cryptohooker.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("$API_VERSION/{pagePath}")
    suspend fun getMovieList(@Path("pagePath") pagePath: String): ApiResponse<ArrayList<MovieModel>>


    @GET("$API_VERSION/{pagePath}")
    suspend fun getNewsFeed(@Path("pagePath") pagePath: String): ApiResponse<ArrayList<MovieModel>>

    companion object {
        const val API_VERSION = "v2"
    }
}