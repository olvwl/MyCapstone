package com.example.core.source.local.remote.network

import com.example.core.source.local.remote.response.ListSportResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("all_sports.php")
    suspend fun getList() : ListSportResponse
}
