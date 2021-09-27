package com.example.cryptohooker.ui.newsfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptohooker.R
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.NewsFeedModel
import com.example.cryptohooker.data.model.UserStoryModel
import com.example.cryptohooker.data.usecases.FetchNewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val fetchNewsFeedUseCase: FetchNewsFeedUseCase
) : ViewModel() {

    private var _newsFeedListModel = MutableLiveData<List<NewsFeedModel>>()
    var newsFeedModelLiveData: LiveData<List<NewsFeedModel>> = _newsFeedListModel

    private var _uiState = MutableLiveData<NewsFeedUiState>()
    var uiStateLiveData: LiveData<NewsFeedUiState> = _uiState

    init {
        fetchNewsFeeds()
    }

    private fun fetchNewsFeeds() {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchNewsFeedUseCase.invoke().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        _newsFeedListModel.postValue(dataState.data!!)
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }

    private var _userStoryLiveListModel = MutableLiveData<List<UserStoryModel>>()
    var userStoryLiveListModel: LiveData<List<UserStoryModel>> = _userStoryLiveListModel

    fun getUserStories() {
        val list: MutableList<UserStoryModel> = ArrayList()
        list.add(UserStoryModel(R.drawable.ic_video_story, "You"))
        list.add(UserStoryModel(R.drawable.ic_man, "Yousuf"))
        list.add(UserStoryModel(R.drawable.ic_woman, "Aliza"))
        list.add(UserStoryModel(R.drawable.ic_man, "Mathews"))
        list.add(UserStoryModel(R.drawable.ic_man, "Ali"))
        list.add(UserStoryModel(R.drawable.ic_woman, "Yulia"))
        list.add(UserStoryModel(R.drawable.ic_man, "Robbert"))
        list.add(UserStoryModel(R.drawable.ic_man, "John"))
        list.add(UserStoryModel(R.drawable.ic_woman, "Ellie Golding"))
        list.add(UserStoryModel(R.drawable.ic_man, "Mike"))
        _userStoryLiveListModel.postValue(list)
    }
}
