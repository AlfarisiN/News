package com.project.news

import NewsAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.project.news.network.RetrofitInstance
import com.project.news.repository.NewsRepository
import com.project.news.viewmodel.NewsViewModel
import com.project.news.viewmodel.NewsViewModelFactory

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarCategory)
        setSupportActionBar(toolbar)
        val category = intent.getStringExtra("category") ?: "unknown"
        val sources = intent.getStringExtra("sources") ?: "bbc"

        supportActionBar?.title = category.replaceFirstChar { it.uppercase() }

        val repository = NewsRepository(RetrofitInstance.api)
        val factory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerNews)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NewsAdapter { article ->
            val intent = Intent(this, NewsDetailActivity::class.java)
            intent.putExtra("url", article.url)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        viewModel.fetchNews(category, "0c541d9f43f44ba98e5d735576adff2f", sources)

        viewModel.news.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}