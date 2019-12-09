package com.georgcantor.newsproject

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@MyApp)
//            modules(arrayListOf(appModule, viewModelModule, repositoryModule))
//        }
    }

}