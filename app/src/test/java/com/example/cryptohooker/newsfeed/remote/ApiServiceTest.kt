package com.example.cryptohooker.newsfeed.remote

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
    fun `given ApiInterface when the getNewsFeedList is triggered then it returns the movies`() =
        runBlocking {
            // Given
            enqueueResponse("news_feed_response_200.json")

            // Invoke
            val response = cryptoApiService.getNewsFeedList()
            val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
            mockWebServer.takeRequest()

            // Then
            responseBody.forEach {
                assertThat(it.id, `is`(1))
                assertThat(it.createdAt, `is`("Sept 2021, 12:23pm"))
                assertThat(it.userImageUrl, `is`("http://dummyimage.com/500x500.png/ff4444/ffffff"))
                assertThat(it.postImage, `is`("http://dummyimage.com/500x500.png/ff4444/ffffff"))
                assertThat(it.postMessage, `is`("Lorem ipsum is the best site to get thet dihskiyd ilhoi hoiuk"))
                assertThat(it.userFullName, `is`("Lorem ipsum"))
                assertThat(it.likeCount, `is`(233))
                assertThat(it.commentCount, `is`(233))

            }
        }
}
