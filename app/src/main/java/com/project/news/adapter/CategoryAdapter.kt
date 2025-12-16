package com.project.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.news.R
import com.project.news.model.NewsCategoryModel

class CategoryAdapter(
    private val onClick: (NewsCategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val category = mutableListOf<NewsCategoryModel>()

    fun submitList(list: List<NewsCategoryModel>) {
        category.clear()
        category.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(category[position])
    }

    override fun getItemCount() = category.size

    inner class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(category: NewsCategoryModel) {
            itemView.findViewById<TextView>(R.id.titleCategory).text = category.name
            itemView.findViewById<ImageView>(R.id.iconCategory)
                .setImageResource(category.iconRes)

            itemView.setOnClickListener {
                onClick(category)
            }
        }
    }
}