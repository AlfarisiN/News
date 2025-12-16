package com.project.news.model

data class NewsResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)