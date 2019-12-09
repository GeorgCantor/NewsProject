package com.georgcantor.newsproject

import android.app.Application
import com.georgcantor.newsproject.di.appModule
import com.georgcantor.newsproject.di.repositoryModule
import com.georgcantor.newsproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(arrayListOf(appModule, viewModelModule, repositoryModule))
        }
    }

}