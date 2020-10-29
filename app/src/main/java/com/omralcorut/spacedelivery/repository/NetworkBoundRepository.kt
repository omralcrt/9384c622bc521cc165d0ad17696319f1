package com.omralcorut.spacedelivery.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.omralcorut.spacedelivery.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {

        emit(State.loading())

        emit(State.success(fetchFromLocal().first()))

        val apiResponse = fetchFromRemote()

        val remoteData = apiResponse.body()

        if (apiResponse.isSuccessful && remoteData != null) {
            if (isLocalEmpty()) {
                saveRemoteData(remoteData)
            }
        } else {
            emit(State.error(apiResponse.message()))
        }

        emitAll(
            fetchFromLocal().map {
                State.success(it)
            }
        )
    }.catch { e ->
        emit(State.error("Network error! Can't get data"))
        e.printStackTrace()
    }

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    @MainThread
    protected abstract suspend fun isLocalEmpty(): Boolean

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>


}