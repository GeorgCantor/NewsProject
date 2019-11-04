package com.georgcantor.newsproject.model.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("source")
    val source: String = "",

    @SerializedName("sortBy")
    val sortBy: String = "",

    @SerializedName("articles")
    val articles: List<Article> = emptyList()
)