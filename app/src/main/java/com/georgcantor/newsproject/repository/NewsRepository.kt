package com.georgcantor.newsproject.repository

import com.georgcantor.newsproject.BuildConfig
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.model.local.NewsDao
import com.georgcantor.newsproject.model.remote.ApiService
import retrofit2.await

class NewsRepository(
    private val apiService: ApiService,
    private val dao: NewsDao
)  {

    suspend fun getNews(): List<Article> {
        dao.deleteAllArticles()

        val news = apiService.getNews(BuildConfig.API_KEY).await()

        news.articles.map {
            val article = Article(
                id = it.hashCode(),
                title = it.title,
                description = it.description,
                imageUrl = it.urlToImage,
                url = it.url,
                source = it.source.name,
                publishedAt = it.publishedAt
            )
            dao.insertArticle(article)
        }

        return dao.getAllArticles()
    }

    suspend fun getArticleById(id: Int): Article? = dao.getArticleById(id)

}