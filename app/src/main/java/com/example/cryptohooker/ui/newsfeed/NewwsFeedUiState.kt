package com.example.cryptohooker.ui.newsfeed

sealed class DetailsUiState

object LoadingState : DetailsUiState()
object ContentState : DetailsUiState()
class ErrorState(val message: String) : DetailsUiState()
