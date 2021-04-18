package `in`.summachar.android.ui

import `in`.summachar.android.databinding.ArticleListViewBinding
import `in`.summachar.android.models.Article
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TopStoriesAdapterClickable(val clickListener:ArticleListener) : androidx.recyclerview.widget.ListAdapter<Article, TopStoriesAdapterClickable.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ArticleListViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, clickListener: ArticleListener){

            binding.article = article
            binding.clickListener = clickListener

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArticleListViewBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)

        holder.bind(article, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}


class ArticleListener(val clickListener: (url:String?) -> Unit) {

    fun onClick(article: Article?) = clickListener(article?.url)
}

class DiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}
