package com.georgcantor.newsproject.datasource

import androidx.paging.DataSource
import com.georgcantor.newsproject.model.data.News
import com.georgcantor.newsproject.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(
    repository: NewsRepository,
    scope: CoroutineScope
) : DataSource.Factory<Int, News>() {

    override fun create(): DataSource<Int, News> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}