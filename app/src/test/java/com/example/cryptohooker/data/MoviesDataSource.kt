package com.example.cryptohooker.data

import com.example.cryptohooker.data.model.MovieModel

object MoviesDataSource {

    fun getMovieList(): ArrayList<MovieModel> {
        val movieList = ArrayList<MovieModel>()
        movieList.add(movieModel)
        return movieList
    }

    private val movieModel: MovieModel
        get() = MovieModel(
            id = 1,
            movieImage = "http://dummyimage.com/500x500.png/5fa2dd/ffffff",
            movieYear = 2006,
            movieGenre = "Comedy",
            isRecommended = true,
            isPopular = true,
            movieTitle = "Lorem ipsumTitle"
        )
}