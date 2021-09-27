package com.example.cryptohooker.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.usecases.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The HomeViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<MovieUiState>()
    var uiStateLiveData: LiveData<MovieUiState> = _uiState

    var recommendedMoviesLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData<List<MovieModel>>()
    var popularMoviesLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData<List<MovieModel>>()

    init {
        fetchProducts()
    }

    @SuppressLint("NullSafeMutableLiveData")
    private fun fetchProducts() {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchMoviesUseCase.invoke().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        getFilteredMovies(dataState.data)
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }

    private fun getFilteredMovies(movies: List<MovieModel>) {
        val popularMovies: MutableList<MovieModel> = ArrayList()
        val recommendedMovies: MutableList<MovieModel> = ArrayList()

        movies.forEach {
            if (it.isPopular)
                popularMovies.add(it)
            if (it.isRecommended)
                recommendedMovies.add(it)
        }

        _uiState.postValue(ContentState)
        recommendedMoviesLiveData.value = recommendedMovies
        popularMoviesLiveData.value = popularMovies
    }
}
