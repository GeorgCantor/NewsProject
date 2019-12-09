package com.georgcantor.newsproject.di

import com.georgcantor.newsproject.model.local.NewsDatabase
import com.georgcantor.newsproject.model.remote.ApiClient
import com.georgcantor.newsproject.repository.NewsRepository
import com.georgcantor.newsproject.viewmodel.ViewModelFactory
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
    single { ViewModelFactory(get()) }
}

val viewModelModule = module {
}

val appModule = module {
    single { ApiClient.create() }
    single { NewsDatabase.getDatabase(get()).newsDao() }
}