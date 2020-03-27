package com.georgcantor.newsproject.repository

import com.georgcantor.newsproject.model.remote.ApiService

class NewsRepository(private val apiService: ApiService) {

    suspend fun getNews(
        query: String,
        page: Int
    ) = apiService.getNewsAsync(query, page).await().articles
}