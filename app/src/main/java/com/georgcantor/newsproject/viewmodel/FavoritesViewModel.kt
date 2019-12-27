package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.MutableLiveData
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.model.local.ArticleDao
import kotlinx.coroutines.launch

class FavoritesViewModel(private val dao: ArticleDao) : BaseViewModel() {

    val articles = MutableLiveData<List<Article>>()

    fun insert(article: Article) {
        ioScope.launch {
            dao.insert(article)
        }
    }

    fun deleteByUrl(url: String) {
        ioScope.launch {
            dao.deleteByUrl(url)
        }
    }

    fun deleteAll() {
        ioScope.launch {
            dao.deleteAll()
        }
    }

    fun getByUrl(url: String) {
        ioScope.launch {
            articles.postValue(dao.getByUrl(url))
        }
    }

    fun getAllArticles() {
        ioScope.launch {
            articles.postValue(dao.getAll())
        }
    }

}