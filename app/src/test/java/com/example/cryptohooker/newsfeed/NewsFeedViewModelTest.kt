package com.example.cryptohooker.newsfeed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.NewsFeedDataSource
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.model.NewsFeedModel
import com.example.cryptohooker.data.usecases.FetchNewsFeedUseCase
import com.example.cryptohooker.ui.movies.ContentState
import com.example.cryptohooker.ui.newsfeed.NewsFeedUiState
import com.example.cryptohooker.ui.newsfeed.NewsFeedViewModel
import com.example.cryptohooker.utils.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsFeedViewModelTest {

    // Subject under test
    private lateinit var sut: NewsFeedViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @MockK
    lateinit var fetchNewsFeedUseCase: FetchNewsFeedUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, new posts-newsfeed are fetched`() = runBlocking {
        // Given
        val givenMovies = NewsFeedDataSource.getNewsFeedList()
        val uiObserver = mockk<Observer<NewsFeedUiState>>(relaxed = true)
        val newsListObserver = mockk<Observer<List<NewsFeedModel>>>(relaxed = true)

        // When
        coEvery { fetchNewsFeedUseCase.invoke() }
            .returns(flowOf(DataState.success(givenMovies)))

        // Invoke
        sut = NewsFeedViewModel(fetchNewsFeedUseCase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.newsFeedModelLiveData.observeForever(newsListObserver)

        // Then
        coVerify(exactly = 1) { fetchNewsFeedUseCase.invoke() }
        verify { newsListObserver.onChanged(match { it.size == givenMovies.size }) }
    }
}
