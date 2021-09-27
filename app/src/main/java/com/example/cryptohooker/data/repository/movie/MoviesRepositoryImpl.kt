package com.example.cryptohooker.data.repository.movie

import androidx.annotation.WorkerThread
import com.example.cryptohooker.base.BaseRepository
import com.example.cryptohooker.core.extensions.noNetworkErrorMessage
import com.example.cryptohooker.core.extensions.somethingWentWrong
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.MovieModel
import com.example.cryptohooker.data.remote.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * This is an implementation of [MoviesRepository] to handle communication with [ApiInterface] server.
 * @author Malik Dawar
 */
class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface
) : MoviesRepository, BaseRepository() {

    @WorkerThread
    override suspend fun getMovieList(): Flow<DataState<ArrayList<MovieModel>>> {
        return flow {
            apiService.getMovieList().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<ArrayList<MovieModel>>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<ArrayList<MovieModel>>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<ArrayList<MovieModel>>(somethingWentWrong()))
                }
            }
        }
    }
}