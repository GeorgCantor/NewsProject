package com.georgcantor.newsproject.di

import com.georgcantor.newsproject.model.remote.ApiClient
import com.georgcantor.newsproject.repository.NewsRepository
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get()) }
}

val viewModelModule = module {
    viewModel { (query: String) ->
        NewsViewModel(get(), query)
    }
}

val appModule = module {
    single { ApiClient.create(get()) }
}