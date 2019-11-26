package com.georgcantor.newsproject.repo

import com.georgcantor.newsproject.model.ViewState
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.local.NewsDao
import com.georgcantor.newsproject.model.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val dao: NewsDao,
    private val apiService: ApiService
) {

    fun getNews(): Flow<ViewState<List<Article>>> {
        return flow {
            emit(ViewState.loading())
            emit(ViewState.success(dao.getArticles()))

            dao.insertArticles(apiService.getNews().articles)

            emit(ViewState.success(dao.getArticles()))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

}