package com.georgcantor.newsproject.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.georgcantor.newsproject.model.data.Article.Article.tableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = tableName)
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = Article.Column.author)
    @SerializedName(Article.Column.author)
    val author: String? = null,


    @ColumnInfo(name = Article.Column.title)
    @SerializedName(Article.Column.title)
    val title: String? = null,

    @ColumnInfo(name = Article.Column.description)
    @SerializedName(Article.Column.description)
    val description: String? = null,

    @ColumnInfo(name = Article.Column.url)
    @SerializedName(Article.Column.url)
    val url: String? = null,

    @ColumnInfo(name = Article.Column.urlToImage)
    @SerializedName(Article.Column.urlToImage)
    val urlToImage: String? = null,

    @ColumnInfo(name = Article.Column.publishedAt)
    @SerializedName(Article.Column.publishedAt)
    val publishedAt: String? = null
) {

    object Article {
        const val tableName = "article"

        object Column {
            const val id = "id"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
        }
    }

}