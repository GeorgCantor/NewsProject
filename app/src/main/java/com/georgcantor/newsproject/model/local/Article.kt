package com.georgcantor.newsproject.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.georgcantor.newsproject.model.local.Article.Article.tableName

@Entity(tableName = tableName)
data class Article(
    var title: String,
    var description: String,
    @PrimaryKey
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var content: String
) {

    object Article {
        const val tableName = "articles"

        object Column {
            var title = "title"
            var description = "description"
            var url = "url"
            var urlToImage = "urlToImage"
            var publishedAt = "publishedAt"
            var content = "content"
        }
    }

}