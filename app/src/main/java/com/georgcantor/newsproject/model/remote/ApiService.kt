package com.georgcantor.newsproject.model.remote

import com.georgcantor.newsproject.model.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=us")
    fun getNews(
        @Query("apiKey") apiKey: String
    ): Call<News>

}