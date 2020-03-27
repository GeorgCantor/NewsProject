package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagsViewModel : ViewModel() {

    private val tags = MutableLiveData<ArrayList<String>>()

    fun getTags(): LiveData<ArrayList<String>> {
        tags.value = arrayListOf(
            "Science",
            "Politics",
            "Finance",
            "Sport",
            "World",
            "USA",
            "Russia",
            "Europe",
            "NBA",
            "NFL",
            "NHL",
            "Cars",
            "Business",
            "Lifestyle",
            "China",
            "MLB"
        )

        return tags
    }
}