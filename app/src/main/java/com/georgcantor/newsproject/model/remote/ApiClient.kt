package com.georgcantor.newsproject.model.remote

import android.content.Context
import com.georgcantor.newsproject.BuildConfig
import com.georgcantor.newsproject.model.remote.interceptor.OfflineResponseCacheInterceptor
import com.georgcantor.newsproject.model.remote.interceptor.ResponseCacheInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var interceptor: HttpLoggingInterceptor

    fun create(context: Context): ApiService {

        if (BuildConfig.DEBUG) {
            interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(ResponseCacheInterceptor())
            .addInterceptor(OfflineResponseCacheInterceptor(context))
            .addInterceptor(interceptor)
            .cache(Cache(File(context.cacheDir, "ResponsesCache"), (10 * 1024 * 1024).toLong()))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

}