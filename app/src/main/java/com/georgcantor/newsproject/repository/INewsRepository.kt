package com.georgcantor.newsproject.repository

import com.georgcantor.newsproject.model.local.NewsArticle

interface INewsRepository {

    suspend fun getNews(): List<NewsArticle>

    suspend fun getNewsArticle(id: Int): NewsArticle?

}
