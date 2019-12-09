package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.repository.INewsRepository

class DetailsViewModel(private val repository: INewsRepository) : NewsViewModel() {

    private val articleLiveData = MutableLiveData<Article>()

    val newsArticle: LiveData<Article>
        get() = articleLiveData

    fun getArticleById(id: Int) {
        loadData({
            repository.getArticleById(id)
        }) {
            articleLiveData.value = it
        }
    }

}