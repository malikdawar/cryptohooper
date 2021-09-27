package com.example.cryptohooker.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.MoviesDataSource
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.usecases.FetchMoviesUseCase
import com.example.cryptohooker.ui.movies.ContentState
import com.example.cryptohooker.ui.movies.MovieUiState
import com.example.cryptohooker.ui.movies.MoviesViewModel
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
class MoviesViewModelTest {

    // Subject under test
    private lateinit var sut: MoviesViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @MockK
    lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, movies are fetched`() = runBlocking {
        // Given
        val givenMovies = MoviesDataSource.getMovieList()
        val uiObserver = mockk<Observer<MovieUiState>>(relaxed = true)
        val movieListObserver = mockk<Observer<List<MovieModel>>>(relaxed = true)

        // When
        coEvery { fetchMoviesUseCase.invoke() }
            .returns(flowOf(DataState.success(givenMovies)))

        // Invoke
        sut = MoviesViewModel(fetchMoviesUseCase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.recommendedMoviesLiveData.observeForever(movieListObserver)
        sut.popularMoviesLiveData.observeForever(movieListObserver)

        // Then
        coVerify(exactly = 1) { fetchMoviesUseCase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { movieListObserver.onChanged(match { it.size == givenMovies.size }) }
    }
}
