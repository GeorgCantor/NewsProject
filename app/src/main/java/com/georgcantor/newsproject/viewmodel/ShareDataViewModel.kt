package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georgcantor.newsproject.model.data.Article

class ShareDataViewModel : ViewModel() {

    val article = MutableLiveData<Article>()

    fun setArticle(article: Article) {
        this.article.value = article
    }

}