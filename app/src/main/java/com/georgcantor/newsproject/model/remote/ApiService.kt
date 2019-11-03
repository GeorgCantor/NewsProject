package com.georgcantor.newsproject.model.remote

import com.georgcantor.newsproject.model.data.NewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("articles?source=google-news&apiKey=777")
    suspend fun getNews(): NewsResponse

}