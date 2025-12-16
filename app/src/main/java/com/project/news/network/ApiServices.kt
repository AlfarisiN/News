package com.project.news.network

import com.project.news.model.NewsResponseModel
import com.project.news.model.SourcesResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServices {

    @Headers(
        "User-Agent: NewsApp-Android",
        "Accept: application/json"
    )
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("source") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsResponseModel

    @Headers(
        "User-Agent: NewsApp-Android",
        "Accept: application/json"
    )
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): SourcesResponseModel
}