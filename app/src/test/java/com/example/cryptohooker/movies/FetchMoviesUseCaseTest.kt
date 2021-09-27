package com.example.cryptohooker.movies

import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.MoviesDataSource
import com.example.cryptohooker.data.repository.movies.MoviesRepository
import com.example.cryptohooker.data.usecases.FetchMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchMoviesUseCaseTest {

    @MockK
    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given when the invoke in FetchMoviesUseCase is called then it fetch the movies`() =
        runBlocking {
            // Given
            val sut = FetchMoviesUseCase(repository)
            val givenMovies = MoviesDataSource.getMovieList()

            // When
            coEvery { repository.fetchMovieList() }
                .returns(flowOf(DataState.success(givenMovies)))

            // Invoke
            val movieListFlow = sut()

            // Then
            MatcherAssert.assertThat(movieListFlow, CoreMatchers.notNullValue())

            val movieListDataState = movieListFlow.first()
            MatcherAssert.assertThat(movieListDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                movieListDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java)
            )

            val movieList = (movieListDataState as DataState.Success).data
            MatcherAssert.assertThat(movieList, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(movieList.size, `is`(givenMovies.size))
        }
}
