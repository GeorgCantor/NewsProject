package com.georgcantor.newsproject.di

import com.georgcantor.newsproject.model.local.NewsDatabase
import com.georgcantor.newsproject.model.remote.ApiClient
import com.georgcantor.newsproject.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}

val viewModelModule = module {
//    viewModel {
//        NewsViewModel(get())
//    }
}

val appModule = module {
    single { ApiClient.create() }
    single { NewsDatabase.getDatabase(get()).newsDao() }
}