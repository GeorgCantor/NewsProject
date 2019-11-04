package com.georgcantor.newsproject.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.georgcantor.newsproject.model.data.Article

@Database(entities = [Article::class], version = DatabaseMigration.latestVersion)
abstract class NewsDatabase : RoomDatabase() {

    companion object {
        private const val dbName = "news_db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, dbName)
                .addMigrations(*DatabaseMigration.allMigrations)
                .build()
    }

    abstract fun dao(): NewsDao

}