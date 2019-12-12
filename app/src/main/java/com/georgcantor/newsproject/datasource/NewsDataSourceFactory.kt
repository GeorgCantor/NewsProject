package com.georgcantor.newsproject.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(
    repository: NewsRepository,
    scope: CoroutineScope
) : DataSource.Factory<Int, Article>() {

    val source = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Article> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}