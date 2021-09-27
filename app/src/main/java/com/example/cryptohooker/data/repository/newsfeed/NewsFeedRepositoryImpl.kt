package com.example.cryptohooker.data.repository.newsfeed

import androidx.annotation.WorkerThread
import com.example.cryptohooker.core.extensions.noNetworkErrorMessage
import com.example.cryptohooker.core.extensions.somethingWentWrong
import com.example.cryptohooker.data.DataState
import com.example.cryptohooker.data.model.NewsFeedModel
import com.example.cryptohooker.data.remote.*
import com.example.cryptohooker.data.remote.apiservice.CryptoApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * This is an implementation of [NewsFeedRepository] to handle communication with [CryptoApiInterface] server.
 * @author Malik Dawar
 */
class NewsFeedRepositoryImpl @Inject constructor(
    private val cryptoApiInterface: CryptoApiInterface
) : NewsFeedRepository {

    @WorkerThread
    override suspend fun fetchNewsFeedList():
            Flow<DataState<ArrayList<NewsFeedModel>>> {
        return flow {
            cryptoApiInterface.getNewsFeedList().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
            }.onErrorSuspend {
                emit(DataState.error<ArrayList<NewsFeedModel>>(message()))
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<ArrayList<NewsFeedModel>>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<ArrayList<NewsFeedModel>>(somethingWentWrong()))
                }
            }
        }
    }
}