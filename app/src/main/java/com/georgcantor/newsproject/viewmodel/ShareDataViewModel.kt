package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.util.Constants.TAGS
import com.georgcantor.newsproject.util.PreferenceManager

class ShareDataViewModel(private val prefManager: PreferenceManager?) : ViewModel() {

    private var mainTags = MutableLiveData<ArrayList<String>?>()
    val article = MutableLiveData<Article>()
    val query = MutableLiveData<String>()
    val isFabVisible = MutableLiveData<Boolean>()

    fun setArticle(article: Article) {
        this.article.value = article
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun setMainTags(tags: ArrayList<String>) {
        prefManager?.saveStringList(TAGS, tags)
    }

    fun getMainTags(): LiveData<ArrayList<String>> {
        getTagsFromPrefs()

        return mainTags as LiveData<ArrayList<String>>
    }

    private fun getTagsFromPrefs() {
        mainTags.value = prefManager?.getStringList(TAGS)
    }

    fun setFabVisibility(isVisible: Boolean) {
        isFabVisible.value = isVisible
    }
}