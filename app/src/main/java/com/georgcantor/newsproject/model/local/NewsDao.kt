package com.georgcantor.newsproject.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM Article where id == :id")
    suspend fun getArticleById(id: Int): Article

    @Query("DELETE FROM Article")
    suspend fun deleteAllArticles()

}