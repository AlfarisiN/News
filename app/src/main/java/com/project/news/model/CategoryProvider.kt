package com.project.news.model

import com.project.news.R

object CategoryProvider {
    val categories = listOf(
        NewsCategoryModel("Business", "business", R.drawable.cooperation),
        NewsCategoryModel("Entertainment", "entertainment", R.drawable.cinema),
        NewsCategoryModel("General", "general", R.drawable.school),
        NewsCategoryModel("Health", "health", R.drawable.healthcare),
        NewsCategoryModel("Science", "science", R.drawable.science),
        NewsCategoryModel("Sports", "sports", R.drawable.sports),
        NewsCategoryModel("Technology", "technology", R.drawable.cpu)
    )
}
