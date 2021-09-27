package com.example.cryptohooker.ui.newsfeed

sealed class NewsFeedUiState

object LoadingState : NewsFeedUiState()
object ContentState : NewsFeedUiState()
class ErrorState(val message: String) : NewsFeedUiState()
