package com.georgcantor.newsproject.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Article::class],
    version = DatabaseMigration.latestVersion,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {

    companion object {
        private const val dbName = "article_db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, ArticleDatabase::class.java, dbName)
                .addMigrations(*DatabaseMigration.allMigrations)
                .build()
    }

    abstract fun dao(): ArticleDao
}