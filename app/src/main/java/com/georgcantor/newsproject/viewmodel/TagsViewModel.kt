package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagsViewModel : ViewModel() {

    val tags = MutableLiveData<ArrayList<String>>()

    fun setTags() {
        val tags = arrayListOf(
            "Science",
            "Politics",
            "Finance",
            "Sport",
            "World",
            "USA",
            "Russia",
            "Trump"
        )
        this.tags.value = tags
    }

}