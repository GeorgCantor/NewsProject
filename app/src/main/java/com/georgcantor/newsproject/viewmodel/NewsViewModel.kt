package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.georgcantor.newsproject.datasource.NewsDataSource
import com.georgcantor.newsproject.datasource.NewsDataSourceFactory
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.local.ArticleDao
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    repository: NewsRepository,
    private val dao: ArticleDao,
    query: String
) : BaseViewModel() {

    private val newsDataSource = NewsDataSourceFactory(repository, query, ioScope)

    val news = LivePagedListBuilder(newsDataSource, pagedListConfig()).build()

    val networkState: LiveData<NetworkState>? = Transformations.switchMap(
        newsDataSource.source,
        NewsDataSource::getNetworkState
    )

    fun getNews() = newsDataSource.create()

    fun saveNews(article: Article) {
        ioScope.launch {
            dao.insert(
                com.georgcantor.newsproject.model.local.Article(
                    title = article.title,
                    description = article.description,
                    url = article.url,
                    urlToImage = article.urlToImage,
                    publishedAt = article.publishedAt,
                    content = article.content
                )
            )
        }
    }

    fun retry() = newsDataSource.source.value?.retryFailedQuery()

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()

}