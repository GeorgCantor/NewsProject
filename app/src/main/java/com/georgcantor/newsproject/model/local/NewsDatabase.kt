package com.georgcantor.newsproject.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsArticle::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        lateinit var INSTANCE: NewsDatabase

        fun getDatabase(context: Context): NewsDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(NewsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "news_db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }

}
