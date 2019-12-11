package com.georgcantor.newsproject.model.data

data class Article(

    var title: String,

    var description: String,

    var url: String,

    var urlToImage: String,

    var publishedAt: String,

    var source: Source

)