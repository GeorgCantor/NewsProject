package com.georgcantor.newsproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE id = :id")
    fun getArticle(id: Long): LiveData<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<Article>)

    @Query("DELETE FROM news")
    fun deleteNews()

}