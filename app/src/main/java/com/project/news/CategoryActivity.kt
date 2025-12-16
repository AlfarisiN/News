package com.project.news

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.project.news.adapter.CategoryAdapter
import com.project.news.model.SourceDto
import com.project.news.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var cat: String

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "News Categories"

        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        adapter = CategoryAdapter{
            category ->
            viewModel.loadSources(category.apiValue, "0c541d9f43f44ba98e5d735576adff2f")
            cat = category.apiValue

        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCategory)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.category.observe(this) {
            adapter.submitList(it)
        }
        viewModel.sources.observe(this) { sources ->
            if (sources.isNotEmpty()) {
                showSourceDialog(sources, cat)
            }
        }

    }

    private fun showSourceDialog(sources: List<SourceDto>, category: String) {

        val sourceNames = sources.map { it.name }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Select News Source")
            .setItems(sourceNames) { _, which ->
                val sourceId = sources[which].id
                openNews(sourceId, category)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun openNews(sourceId: String?, category: String?) {
        val intent = Intent(this, NewsActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("source", sourceId)
        startActivity(intent)
    }
}