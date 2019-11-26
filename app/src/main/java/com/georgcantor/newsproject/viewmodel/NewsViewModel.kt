package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.georgcantor.newsproject.model.ViewState
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.repo.Repository

class NewsViewModel(repository: Repository) : ViewModel() {

    private val articles: LiveData<ViewState<List<Article>>> = repository.getNews().asLiveData()

    fun getArticles(): LiveData<ViewState<List<Article>>> = articles

}