package com.georgcantor.newsproject.di

import com.georgcantor.newsproject.model.local.NewsDatabase
import com.georgcantor.newsproject.model.remote.ApiClient
import com.georgcantor.newsproject.repo.Repository
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }
}

val appModule = module {
    single { ApiClient.create() }
    single { NewsDatabase.buildDefault(get()).dao() }
}