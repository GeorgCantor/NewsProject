package com.georgcantor.newsproject.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article): Long

    @Query("DELETE FROM articles WHERE url = :url")
    fun deleteByUrl(url: String)

    @Query("DELETE FROM articles")
    fun deleteAll()

    @Query("SELECT * FROM articles WHERE url LIKE :url")
    fun getByUrl(url: String): List<Article>

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

}