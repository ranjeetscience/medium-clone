package com.example.conduit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.model.entity.Article
import com.example.conduit.databinding.FeedLayoutItemBinding

class FeedListAdapter(
    val onFavoriteClicked: (article: Article) -> Unit,
    val onArticleClicked: (slug: String) -> Unit
) :
    ListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(
            FeedLayoutItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ), onArticleClicked, onFavoriteClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        (holder as ArticleViewHolder).bind(article)
    }

    class ArticleViewHolder(
        private val binding: FeedLayoutItemBinding,
        onArticleClicked: (slug: String) -> Unit,
        onFavoriteClicked: (slug: Article) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivFavourite.setOnClickListener {
                binding.article?.let {
                    onFavoriteClicked(it)
                }
            }
            binding.setItemClickListener {
                binding.article?.let {
                    onArticleClicked(it.slug)
                }
            }
        }

        fun bind(item: Article) {
            binding.apply {
                article = item
                executePendingBindings()
            }
        }

    }

}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.updatedAt == newItem.updatedAt
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}