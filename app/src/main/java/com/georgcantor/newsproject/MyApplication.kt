package com.georgcantor.newsproject

import android.app.Application

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    private val appComponent: AppComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getApplicationComponent(): AppComponent = appComponent

}