package com.georgcantor.newsproject.model.remote

import com.georgcantor.newsproject.BuildConfig
import com.georgcantor.newsproject.model.data.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getNewsAsync(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Deferred<News>

}