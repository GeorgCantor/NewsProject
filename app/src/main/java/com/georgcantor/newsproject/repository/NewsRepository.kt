package com.georgcantor.newsproject.repository

import com.georgcantor.newsproject.model.remote.ApiService

class NewsRepository(private val apiService: ApiService) {

    suspend fun getNews(page: Int) = apiService.getNewsAsync(page).await().articles

}