package com.georgcantor.newsproject.model.local

import androidx.room.migration.Migration

internal object DatabaseMigration {

    const val latestVersion = 1

    val allMigrations: Array<Migration>
        get() = arrayOf()

    object V1 {
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

}