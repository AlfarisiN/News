package com.project.news.viewmodel

import androidx.compose.ui.input.key.Key
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.news.model.CategoryProvider
import com.project.news.model.NewsCategoryModel
import com.project.news.model.SourceDto
import com.project.news.network.RetrofitInstance
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val api = RetrofitInstance.api

    private val _category = MutableLiveData<List<NewsCategoryModel>>()
    val category: LiveData<List<NewsCategoryModel>> = _category

    private val _sources = MutableLiveData<List<SourceDto>>()
    val sources: LiveData<List<SourceDto>> = _sources

    init {
        loadCategory()
    }

    private fun loadCategory() {
        _category.value = CategoryProvider.categories
    }

    fun loadSources(category: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = api.getSources(
                    category = category,
                    apiKey = apiKey
                )
                _sources.value = response.sources
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}