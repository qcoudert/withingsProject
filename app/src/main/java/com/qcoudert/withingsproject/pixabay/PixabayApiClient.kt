package com.qcoudert.withingsproject.pixabay

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PixabayApiClient {
    private const val BASE_URL: String =
        "https://pixabay.com/api/"
    private const val API_KEY: String = "18021445-326cf5bcd3658777a9d22df6f"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                request.url(
                    it.request().url().newBuilder()
                        .addQueryParameter("key", API_KEY).build()
                )
                it.proceed(request.build())
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val pixabayApiService: PixabayApiService by lazy {
        retrofit.create(PixabayApiService::class.java)
    }
}