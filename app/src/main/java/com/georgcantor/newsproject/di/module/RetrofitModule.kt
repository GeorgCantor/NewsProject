package com.georgcantor.newsproject.di.module

import com.georgcantor.newsproject.BuildConfig
import com.georgcantor.newsproject.di.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @ApplicationScope
    fun provideOkHttpCLient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapterFactory(ItemTypeAdapterFactory())
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()

    @Provides
    @ApplicationScope
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val url = request
            .url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val newRequest = request
            .newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }

}

