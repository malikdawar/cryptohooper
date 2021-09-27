package com.example.cryptohooker.movies.remote

import com.example.cryptohooker.ApiAbstract
import com.example.cryptohooker.data.remote.apiservice.CryptoApiInterface
import com.example.cryptohooker.data.remote.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ApiServiceTest : ApiAbstract<CryptoApiInterface>() {

    private lateinit var cryptoApiService: CryptoApiInterface

    @Before
    fun setUp() {
        cryptoApiService = createService(CryptoApiInterface::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `given ApiInterface when the getMovieList is triggered then it returns the movies`() =
        runBlocking {
            // Given
            enqueueResponse("movies_response_200.json")

            // Invoke
            val response = cryptoApiService.getMovieList()
            val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
            mockWebServer.takeRequest()

            // Then
            responseBody.forEach {
                assertThat(it.id, `is`(1))
                assertThat(it.movieImage, `is`("http://dummyimage.com/500x500.png/5fa2dd/ffffff"))
                assertThat(it.movieYear, `is`(2006))
                assertThat(it.movieGenre, `is`("Comedy"))
                assertThat(it.isRecommended, `is`(true))
                assertThat(it.isPopular, `is`(true))
                assertThat(it.movieTitle, `is`("Lorem ipsumTitle"))

            }
        }
}
