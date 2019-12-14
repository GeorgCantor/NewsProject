package com.georgcantor.newsproject.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(
    private val repository: NewsRepository,
    private var query: String,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Article>() {

    val source = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = NewsDataSource(repository, query, scope)
        this.source.postValue(source)

        return source
    }

}