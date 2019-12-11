package com.georgcantor.newsproject.di.module

import android.app.Application
import androidx.room.Room
import com.georgcantor.newsproject.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule() {

    @Provides
    @ApplicationScope
    fun provideNewsDb(application: Application) =
        Room.databaseBuilder(
            application,
            NewsDatabase::class.java, "news_db"
        ).build()

    @Provides
    @ApplicationScope
    fun provideNewsDao(newsDb: NewsDatabase) = newsDb.newsDao()

}