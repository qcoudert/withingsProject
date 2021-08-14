package com.qcoudert.withingsproject.pixabay

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {

    @GET(".")
    suspend fun getHits(@Query("q") query: String): Response<PixabayQueryResponse>

}