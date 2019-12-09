package com.georgcantor.newsproject.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun insertArticle(article: NewsArticle)

    @Delete
    suspend fun deleteArticle(article: NewsArticle)

    @Query("SELECT * FROM NewsArticle")
    suspend fun getAllArticles(): List<NewsArticle>

    @Query("SELECT * FROM NewsArticle where id == :id")
    suspend fun getArticleById(id: Int): List<NewsArticle>

    @Query("DELETE FROM NewsArticle")
    suspend fun deleteAllArticles()

}