package com.georgcantor.newsproject.model.remote

import com.georgcantor.newsproject.model.data.NewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("articles?source=google-news&apiKey=bc0eb2dc0d13435180ee1b514c32606e")
    suspend fun getNews(): NewsResponse

}