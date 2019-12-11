package com.georgcantor.newsproject.di.component

import com.georgcantor.newsproject.di.module.DomainModule
import com.georgcantor.newsproject.di.scope.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [DomainModule::class])
@FragmentScope
interface FragmentComponent {

    fun inject(newsFragment: NewsFragment)

    fun inject(newsDetailFragment: NewsDetailFragment)

}