import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.news.R
import com.project.news.model.ArticleModel

class NewsAdapter(
    private val onItemClick: (ArticleModel) -> Unit
) : ListAdapter<ArticleModel, NewsAdapter.NewsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgNews: ImageView = itemView.findViewById(R.id.imgNews)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(article: ArticleModel) {
            tvTitle.text = article.title
            tvDescription.text = article.description ?: ""

            Glide.with(itemView.context)
                .load(article.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.image)
                .into(imgNews)

            itemView.setOnClickListener {
                onItemClick(article)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}