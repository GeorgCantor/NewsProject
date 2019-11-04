package com.georgcantor.newsproject.model.data

data class NewsArticle(
    val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
)