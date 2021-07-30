package com.example.core.source.local

import com.example.core.source.local.remote.network.ApiResponse
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>{

    private var result: kotlinx.coroutines.flow.Flow<com.example.core.source.local.Resource<ResultType>> = flow {
        emit(com.example.core.source.local.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.example.core.source.local.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { com.example.core.source.local.Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { com.example.core.source.local.Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.example.core.source.local.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.example.core.source.local.Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): kotlinx.coroutines.flow.Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): kotlinx.coroutines.flow.Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): kotlinx.coroutines.flow.Flow<com.example.core.source.local.Resource<ResultType>> = result
}