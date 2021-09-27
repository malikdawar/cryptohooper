package com.example.cryptohooker.di

import com.example.cryptohooker.core.utils.Const
import com.example.cryptohooker.core.utils.Const.REQUEST_TIMEOUT
import com.example.cryptohooker.data.remote.apiservice.CryptoApiInterface
import com.example.cryptohooker.data.remote.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * The Dagger Module to provide the instances of [OkHttpClient], [Retrofit], and [CryptoApiInterface] classes.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun providesProductApiService(okHttpClient: OkHttpClient): CryptoApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_API_URL_PRODUCT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()

        return retrofit.create(CryptoApiInterface::class.java)
    }
}
