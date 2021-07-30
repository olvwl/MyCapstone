package com.example.core.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.source.local.remote.network.ApiResponse
import com.example.core.source.local.remote.network.ApiService
import com.example.core.source.local.remote.response.SportResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Flow


class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getAllSport(): kotlinx.coroutines.flow.Flow<ApiResponse<List<SportResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.places
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.places))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}