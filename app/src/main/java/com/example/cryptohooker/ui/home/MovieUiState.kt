package com.example.cryptohooker.ui.home

sealed class MovieUiState

object LoadingState : MovieUiState()
object ContentState : MovieUiState()
class ErrorState(val message: String) : MovieUiState()
