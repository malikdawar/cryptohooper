package com.example.cryptohooker.ui.movies

sealed class MovieUiState

object LoadingState : MovieUiState()
object ContentState : MovieUiState()
class ErrorState(val message: String) : MovieUiState()
