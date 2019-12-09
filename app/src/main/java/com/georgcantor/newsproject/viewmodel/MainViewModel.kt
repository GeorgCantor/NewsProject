package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.repository.INewsRepository

class MainViewModel(private val repository: INewsRepository) : NewsViewModel() {

    private val newsLiveData = MutableLiveData<List<Article>>()

    val news: LiveData<List<Article>>
        get() = newsLiveData

    fun fetchNews() {
        loadData({
            repository.getNews()
        }) {
            newsLiveData.value = it
        }
    }

}