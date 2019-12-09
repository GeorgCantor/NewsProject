package com.georgcantor.newsproject.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsArticle(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String?,

    val description: String?,

    val url: String?,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "published_at")
    val publishedAt: String?,

    val source: String?

)
