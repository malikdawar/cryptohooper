package com.example.cryptohooker.ui.home

sealed class HomeUiState

object LoadingState : HomeUiState()
object ContentState : HomeUiState()
class ErrorState(val message: String) : HomeUiState()
