package com.georgcantor.newsproject.repository

import com.georgcantor.newsproject.model.local.Article

interface INewsRepository {

    suspend fun getNews(): List<Article>

    suspend fun getArticleById(id: Int): Article?

}
