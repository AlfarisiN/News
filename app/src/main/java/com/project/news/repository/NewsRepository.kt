package com.project.news.repository

import com.project.news.network.ApiServices

class NewsRepository(private val api: ApiServices) {

    suspend fun getNewsByCategory(
        category: String,
        apiKey: String,
        sources: String
    ) = api.getTopHeadlines(category = category, apiKey = apiKey, sources = sources)
}
