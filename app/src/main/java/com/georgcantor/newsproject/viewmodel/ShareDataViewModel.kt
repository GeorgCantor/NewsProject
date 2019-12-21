package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.util.PreferenceManager
import com.georgcantor.newsproject.view.MainActivity.Companion.TAGS

class ShareDataViewModel(private val prefManager: PreferenceManager?) : ViewModel() {

    val article = MutableLiveData<Article>()
    val query = MutableLiveData<String>()
    var mainTags = MutableLiveData<ArrayList<String>?>()

    fun setArticle(article: Article) {
        this.article.value = article
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun setMainTags(tags: ArrayList<String>) {
        prefManager?.saveTags(TAGS, tags)
    }

    fun getMainTags(): LiveData<ArrayList<String>> {
        getTagsFromPrefs()

        return mainTags as LiveData<ArrayList<String>>
    }

    private fun getTagsFromPrefs() {
        mainTags.value = prefManager?.getTags(TAGS)
    }

}