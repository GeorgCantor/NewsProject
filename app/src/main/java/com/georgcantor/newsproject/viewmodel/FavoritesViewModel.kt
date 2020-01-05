package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.model.local.ArticleDao
import kotlinx.coroutines.launch

class FavoritesViewModel(private val dao: ArticleDao) : ViewModel() {

    val articles = MutableLiveData<List<Article>>()

    fun insert(article: Article) {
        viewModelScope.launch {
            dao.insert(article)
        }
    }

    fun deleteByUrl(url: String) {
        viewModelScope.launch {
            dao.deleteByUrl(url)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            dao.deleteAll()
        }
    }

    fun getByUrl(url: String) {
        viewModelScope.launch {
            articles.postValue(dao.getByUrl(url))
        }
    }

    fun getAllArticles() {
        viewModelScope.launch {
            articles.postValue(dao.getAll())
        }
    }

}