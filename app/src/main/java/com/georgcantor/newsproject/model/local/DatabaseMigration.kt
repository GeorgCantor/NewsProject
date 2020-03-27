package com.georgcantor.newsproject.model.local

import androidx.room.migration.Migration

internal object DatabaseMigration {

    const val latestVersion = 1

    val allMigrations: Array<Migration>
        get() = arrayOf()

    object V1 {
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
}