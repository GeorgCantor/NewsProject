package com.georgcantor.newsproject.di.module

import com.georgcantor.newsproject.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @FragmentScope
    fun bindNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository

    @Binds
    @FragmentScope
    fun bindNewsDetailRepository(newsDetailRepository: NewsDetailRepositoryImpl): NewsDetailRepository

}