package com.example.cryptohooker.newsfeed

import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.NewsFeedDataSource
import com.example.cryptohooker.data.repository.newsfeed.NewsFeedRepository
import com.example.cryptohooker.data.usecases.FetchNewsFeedUseCase
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
class FetchNewsFeedUseCaseTest {

    @MockK
    private lateinit var repository: NewsFeedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given when the invoke in FetchNewsFeedUseCase is called then it fetch the newfeed`() =
        runBlocking {
            // Given
            val sut = FetchNewsFeedUseCase(repository)
            val givenNewsList = NewsFeedDataSource.getNewsFeedList()

            // When
            coEvery { repository.fetchNewsFeedList() }
                .returns(flowOf(DataState.success(givenNewsList)))

            // Invoke
            val newsListFlow = sut()

            // Then
            MatcherAssert.assertThat(newsListFlow, CoreMatchers.notNullValue())

            val newsListDataState = newsListFlow.first()
            MatcherAssert.assertThat(newsListDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                newsListDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java)
            )

            val newsList = (newsListDataState as DataState.Success).data
            MatcherAssert.assertThat(newsList, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(newsList.size, `is`(givenNewsList.size))
        }
}
