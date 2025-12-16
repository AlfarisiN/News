package com.project.news.model

data class SourceDto(
    val id: String?,
    val name: String,
    val description: String?,
    val category: String?,
    val language: String?,
    val country: String?
)