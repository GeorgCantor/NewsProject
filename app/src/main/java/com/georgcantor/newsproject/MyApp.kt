package com.georgcantor.newsproject

import android.app.Application
import com.georgcantor.newsproject.di.base.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}