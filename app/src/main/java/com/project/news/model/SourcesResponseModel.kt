package com.project.news.model

data class SourcesResponseModel(
    val status: String,
    val sources: List<SourceDto>
)