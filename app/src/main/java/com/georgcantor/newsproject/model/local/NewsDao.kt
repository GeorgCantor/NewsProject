package com.georgcantor.newsproject.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.georgcantor.newsproject.model.data.Article

@Dao
interface NewsDao {

    @Insert
    fun insertArticles(articles: List<Article>): List<Long>

    @Query("SELECT * FROM article")
    fun getArticles(): List<Article>

}