package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.georgcantor.newsproject.base.BaseViewModel
import com.georgcantor.newsproject.datasource.NewsDataSource
import com.georgcantor.newsproject.datasource.NewsDataSourceFactory
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.repository.NewsRepository

class NewsViewModel(
    repository: NewsRepository,
    query: String
) : BaseViewModel() {

    val article = MutableLiveData<Article>()

    private val newsDataSource = NewsDataSourceFactory(repository, query, ioScope)

    val news = LivePagedListBuilder(newsDataSource, pagedListConfig()).build()

    val networkState: LiveData<NetworkState>? = Transformations.switchMap(
        newsDataSource.source,
        NewsDataSource::getNetworkState
    )

    fun setArticle(article: Article) {
        this.article.value = article
    }

    fun getNews() = newsDataSource.create()

    fun retry() = newsDataSource.source.value?.retryFailedQuery()

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()

}