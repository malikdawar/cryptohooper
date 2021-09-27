package com.example.cryptohooker.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.*
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
class HomeViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _movieList = MutableLiveData<List<MovieModel>>()
    var movieModelLiveData: LiveData<List<MovieModel>> = _movieList

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
                        _uiState.postValue(ContentState)
                        _movieList.postValue(dataState.data)
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }
}
