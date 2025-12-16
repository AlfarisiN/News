package com.project.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.news.model.ArticleModel
import com.project.news.model.NewsResponseModel
import com.project.news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<List<ArticleModel>>()
    val news: LiveData<List<ArticleModel>> = _news

    fun fetchNews(category: String, apiKey: String, sources: String) {
        viewModelScope.launch {
            try {
                println("source : " + sources)
                println("category : " + category)
                val response = repository.getNewsByCategory(category, apiKey, sources)
                _news.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    fun loadNews(source: String?) {
//        viewModelScope.launch {
//            try {
//                val response = api.getTopHeadlines(
//                    sources = source,
//                    apiKey = BuildConfig.NEWS_API_KEY
//                )
//                _news.value = response.articles
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

}